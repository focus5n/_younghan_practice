package younghan.core.discount.discountImpl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import younghan.core.discount.DiscountPolicy;
import younghan.core.member.Grade;
import younghan.core.member.Member;

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            int DISCOUNT_PERCENT = 10;
            return price * DISCOUNT_PERCENT / 100;
        } else {
            return 0;
        }
    }
}
