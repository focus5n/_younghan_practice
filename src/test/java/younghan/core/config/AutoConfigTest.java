package younghan.core.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import younghan.core.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoConfigTest {

    @Test
    @DisplayName("@ComponentScan, @Component 활용하면 자동으로 빈이 주입된다.")
    void basicScan() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = context.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
