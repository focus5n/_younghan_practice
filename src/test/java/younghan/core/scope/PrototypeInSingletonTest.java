package younghan.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeInSingletonTest {
    
    @Test
    @DisplayName("숫자 세기 with 프로토타입 빈")
    void countWithPrototypeBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigPrototype.class);

        ConfigPrototype bean01 = context.getBean(ConfigPrototype.class);
        ConfigPrototype bean02 = context.getBean(ConfigPrototype.class);

        bean01.addCount();
        bean02.addCount();

        assertThat(bean01.getCount()).isEqualTo(1);
        assertThat(bean02.getCount()).isEqualTo(1);
        context.close();
    }

    @Test
    @DisplayName("Provider 사용하면 싱글톤이 의존하는 프로토타입 빈도 매번 주입된다.")
    void singletonBeanWithPrototypeBean() {
        var context = new AnnotationConfigApplicationContext(ConfigPrototype.class, ClientBean.class);

        ClientBean client01 = context.getBean(ClientBean.class);
        int count01 = client01.logic();
        assertThat(count01).isEqualTo(1);

        ClientBean client02 = context.getBean(ClientBean.class);
        int count02 = client02.logic();
        assertThat(count02).isEqualTo(1);
    }

    record ClientBean(Provider<ConfigPrototype> provider) {

        int logic() {
            var configPrototype = provider.get();
            configPrototype.addCount();
            return configPrototype.getCount();
        }
    }

    @Configuration
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Getter
    static class ConfigPrototype {

        private int count = 0;

        void addCount() {
            count++;
        }

        @PostConstruct
        void init() {
            System.out.println("ConfigPrototype.init" + this);
        }

        @PreDestroy
        void destroy() {
            System.out.println("ConfigPrototype.destroy");
        }
    }
}
