package younghan.core.beanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import younghan.core.config.AppConfig;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("등록된 빈 모두 출력하기")
    void printAllBeans() {

        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = context.getBean(beanDefinitionName);
            System.out.println("bean = " + bean);
            System.out.println("beanName = " + beanDefinitionName + "\n");
        }

        System.out.println("Total Beans = " + context.getBeanDefinitionCount());
    }

    @Test
    @DisplayName("애플리케이션 빈만 출력하기")
    void printApplicationBeans() {

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        int counter = 0;

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = context.getBean(beanDefinitionName);
                System.out.println("bean = " + bean);
                System.out.println("beanName = " + beanDefinitionName + "\n");
                counter += 1;
            }
        }

        System.out.println("Total Application Beans = " + counter);
    }

    @Test
    @DisplayName("인프라 빈만 출력하기")
    void printInfraBeans() {

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        int counter = 0;

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                Object bean = context.getBean(beanDefinitionName);
                System.out.println("bean = " + bean);
                System.out.println("beanName = " + beanDefinitionName + "\n");
                counter += 1;
            }
        }

        System.out.println("Total Infra Beans = " + counter);
    }
}
