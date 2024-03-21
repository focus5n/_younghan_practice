package younghan.core.singleton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import younghan.core.config.AppConfig;
import younghan.core.member.MemberService;
import younghan.core.singleton.service.SingletonService;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    AppConfig appConfig;
    AnnotationConfigApplicationContext context;

    @BeforeEach
    void setup() {
        appConfig = new AppConfig();
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    @DisplayName("요청이 들어올 때마다 객체를 생성한다.")
    void makeInstancePerRequest() {
        MemberService memberService01 = appConfig.memberService();
        MemberService memberService02 = appConfig.memberService();

        assertThat(memberService01).isNotSameAs(memberService02);
    }

    @Test
    @DisplayName("싱글톤 객체는 요청이 여러 건 들어와서 단 하나의 객체만 활용한다.")
    void pureContainer() {
        
        // 구현 클래스에 의존하므로 DIP 위반
        // 자식 클래스로 확장하는 것이 어려우므로 OCP 위반
        SingletonService singletonService01 = SingletonService.getInstance();
        SingletonService singletonService02 = SingletonService.getInstance();

        singletonService01.print();
        singletonService02.print();

        assertThat(singletonService01).isSameAs(singletonService02);
    }

    @Test
    @DisplayName("스프링 컨테이너를 활용하여 싱글톤 패턴의 단점을 해결한다")
    void springContainer() {
        MemberService memberService01 = context.getBean("memberService", MemberService.class);
        MemberService memberService02 = context.getBean("memberService", MemberService.class);

        System.out.println("memberService01 = " + memberService01);
        System.out.println("memberService02 = " + memberService02);

        assertThat(memberService01).isSameAs(memberService02);
    }
}
