package younghan.core.discount.discountImpl;

import org.springframework.stereotype.Component;
import younghan.core.discount.DiscountPolicy;
import younghan.core.member.Grade;
import younghan.core.member.Member;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return 1_000;
        }

        else if (member.getGrade() == Grade.BASIC) {
            return 0;
        }

        else {
            return 0;
        }
    }
}
