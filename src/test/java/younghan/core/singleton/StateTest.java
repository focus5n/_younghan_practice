package younghan.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import younghan.core.singleton.service.StatefulService;
import younghan.core.singleton.service.StatelessService;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTest {

    @Configuration
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }


    @Test
    @DisplayName("싱글톤은 필드 값을 공유한다.")
    void singletonShareFields() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService01 = context.getBean("statefulService", StatefulService.class);
        StatefulService statefulService02 = context.getBean("statefulService", StatefulService.class);

        statefulService01.order("user01", 10_040);
        statefulService02.order("user02", 20_250);

        System.out.println("user01 = " + statefulService01.getName());
        System.out.println("user01 = " + statefulService01.getPrice());
        assertThat(statefulService01.getPrice()).isEqualTo(20_250);
    }

    @Test
    @DisplayName("필드를 사용하지 않고 값을 바로 반환하면 싱글톤에서도 문제가 생기지 않는다.")
    void singletonWithoutFields() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService01 = context.getBean("statelessService", StatelessService.class);
        StatelessService statelessService02 = context.getBean("statelessService", StatelessService.class);

        Map<String, Object> user01 = statelessService01.order("user01", 10_040);
        Map<String, Object> user02 = statelessService02.order("user02", 20_250);

        System.out.println("user01 name = " + user01.get("name"));
        System.out.println("user01 price = " + user01.get("price"));
        System.out.println("user02 name = " + user02.get("name"));
        System.out.println("user02 price = " + user02.get("price"));

        assertThat(user01.get("price")).isEqualTo(10_040);
    }
}
