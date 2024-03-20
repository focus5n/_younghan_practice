package younghan.core.beanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import younghan.core.config.AppConfig;
import younghan.core.member.MemberService;
import younghan.core.member.memberImpl.MemberServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        Object memberService = context.getBean("memberService");
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByNameFailure() {
        assertThrows(NoSuchBeanDefinitionException.class,
                    () -> context.getBean("neverFindThisBean"));
    }

    @Test
    @DisplayName("빈 구현타입으로 조회")
    void findBeanByImpl() {
        MemberService memberService = context.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    void findBeanByType() {
        MemberService memberService = context.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
