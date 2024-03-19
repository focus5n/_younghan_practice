package younghan.core.discount.discountImpl;

import younghan.core.discount.DiscountPolicy;
import younghan.core.member.Grade;
import younghan.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private final int DISCOUNT_FIX_AMOUNT = 1_000;
    private final int DISCOUNT_ZERO = 0;
    private final int DISCOUNT_DEFAULT = 0;

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return DISCOUNT_FIX_AMOUNT;
        }

        else if (member.getGrade() == Grade.BASIC) {
            return DISCOUNT_ZERO;
        }

        else {
            return DISCOUNT_DEFAULT;
        }
    }
}
