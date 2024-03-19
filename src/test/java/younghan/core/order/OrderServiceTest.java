package younghan.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import younghan.core.config.AppConfig;
import younghan.core.member.Grade;
import younghan.core.member.Member;
import younghan.core.member.MemberService;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    
    @Test
    @DisplayName("주문 생성")
    void createOrder() {

        // given
        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        // when
        Order orderA = orderService.createOrder(memberId, "itemA", 10_000);

        // then
        Assertions.assertThat(orderA.getDiscountPrice()).isEqualTo(1_000);
    }
}
