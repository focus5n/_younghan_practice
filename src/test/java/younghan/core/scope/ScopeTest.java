package younghan.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class ScopeTest {

    @Test
    @DisplayName("싱글톤")
    void scopeSingleton() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigSingleton.class);

        var bean01 = context.getBean(ConfigSingleton.class);
        var bean02 = context.getBean(ConfigSingleton.class);

        System.out.println("bean01 = " + bean01);
        System.out.println("bean02 = " + bean02);

        assertThat(bean01).isSameAs(bean02);
        context.close();
    }

    @Test
    @DisplayName("프로토타입")
    void scopePrototype() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigPrototype.class);

        System.out.println("init bean01");
        ConfigPrototype bean01 = context.getBean(ConfigPrototype.class);
        System.out.println("init bean01");
        ConfigPrototype bean02 = context.getBean(ConfigPrototype.class);

        System.out.println("bean01 = " + bean01);
        System.out.println("bean02 = " + bean02);

        assertThat(bean01).isNotSameAs(bean02);
        context.close();
    }

    @Configuration
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    static class ConfigSingleton {

        @PostConstruct
        void init() {
            System.out.println("configSingleton.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("configSingleton.destroy");
        }
    }

    @Configuration
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    static class ConfigPrototype {

        @PostConstruct
        void init() {
            System.out.println("configPrototype.init");
        }

        @PreDestroy
        void destroy() {
            System.out.println("configPrototype.destroy");
        }
    }
}
