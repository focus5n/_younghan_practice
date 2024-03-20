package younghan.core.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import younghan.core.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class GenericXmlApplicationContextTest {

    @Test
    @DisplayName("XML 구성파일로 작성해도 정상적으로 빈을 호출할 수 있다.")
    void findBeanByXmlConfig() {
        ApplicationContext context = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = context.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
