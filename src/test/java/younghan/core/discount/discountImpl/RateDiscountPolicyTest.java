package younghan.core.discount.discountImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import younghan.core.config.AppConfig;
import younghan.core.discount.DiscountPolicy;
import younghan.core.member.Grade;
import younghan.core.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy;

    @BeforeEach
    public void beforEach() {
        AppConfig appConfig = new AppConfig();
        discountPolicy = appConfig.discountPolicy();
    }

    @Test
    @DisplayName("VIP 할인되어야 한다")
    void discountVIP() {

        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 20_000);
        System.out.println("DISCOUNT : " + discount);

        // then
        assertThat(discount).isEqualTo(2_000);
    }

    @Test
    @DisplayName("VIP 아니면 할인이 되서는 안된다.")
    void discountBASIC() {

        // given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10_000);
        System.out.println("DISCOUNT : " + discount);

        // then
        assertThat(discount).isEqualTo(0);
    }
}