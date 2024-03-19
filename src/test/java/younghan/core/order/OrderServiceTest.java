package younghan.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import younghan.core.member.Grade;
import younghan.core.member.Member;
import younghan.core.member.MemberService;
import younghan.core.member.memberImpl.MemberServiceImpl;
import younghan.core.order.OrderImpl.OrderServiceImpl;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    
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
