package younghan.core.config.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppTest {

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {}

    @Test
    @DisplayName("includeFilters 걸리는 빈은 스프링 컨테이너가 수집한다.")
    void includeFilterBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = context.getBean("beanA", BeanA.class);

        Assertions.assertThat(beanA).isNotNull();
    }

    @Test
    @DisplayName("excludeFilter 걸리는 빈은 스프링 컨테이너가 수집하지 않는다.")
    void excludeFilterBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean("beanB", BeanB.class));
    }
}
