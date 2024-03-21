package younghan.core.autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import younghan.core.config.AutoAppConfig;
import younghan.core.discount.DiscountPolicy;
import younghan.core.member.Grade;
import younghan.core.member.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeansTest {

    @Test
    @DisplayName("할인정책 정상적으로 작동한다.")
    void findAllBeans() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = context.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int fixDiscount = discountService.discount(member, 10_000, "fixDiscountPolicy");
        int rateDiscount = discountService.discount(member, 20_000, "rateDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixDiscount).isEqualTo(1_000);
        assertThat(rateDiscount).isEqualTo(2_000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
