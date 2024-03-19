package younghan.core.discount.discountImpl;

import younghan.core.discount.DiscountPolicy;
import younghan.core.member.Grade;
import younghan.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private int DISCOUNT_PERCENT = 10;
    private int DISCOUNT_DEFAULT = 0;

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            int calc = price * DISCOUNT_PERCENT / 100;
            return calc;
        } else {
            return price * DISCOUNT_DEFAULT / 100;
        }
    }
}
