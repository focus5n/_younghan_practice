package younghan.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import younghan.core.config.AppConfig;
import younghan.core.member.MemberRepository;
import younghan.core.member.memberImpl.MemberServiceImpl;
import younghan.core.order.OrderImpl.OrderServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("구성파일에서 new 연산자로 여러 번 인스턴스를 생성하려고 해도, 이미 존재한다면 해당 메서드를 실행하지 않는다.")
    void configurationTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = context.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = context.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = context.getBean("memoryMemberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> " + memberRepository1);
        System.out.println("orderService -> " + memberRepository2);
        System.out.println("memberRepository -> " + memberRepository);

        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }
}
