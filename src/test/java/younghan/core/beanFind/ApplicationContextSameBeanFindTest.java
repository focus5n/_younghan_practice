package younghan.core.beanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import younghan.core.member.MemberRepository;
import younghan.core.member.memberImpl.MemoryMemberRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Configuration
    static class SameBeanConfig {


        @Bean
        public MemberRepository memberRepository01() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository02() {
            return new MemoryMemberRepository();
        }
    }
    
    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> context.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있다면, 빈 이름을 지정하면 된다.")
    void findDuplicateBeanByName() {
        MemberRepository memberRepository01 = context.getBean("memberRepository01", MemberRepository.class);
        assertThat(memberRepository01).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입의 빈을 모두 조회하기")
    void findAllBeansByType() {
        Map<String, MemberRepository> beansOfType = context.getBeansOfType(MemberRepository.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("Key = " + key);
            System.out.println("Value = " + beansOfType.get(key).getClass() + "\n");
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }
}
