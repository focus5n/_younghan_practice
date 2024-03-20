package younghan.core.order.OrderImpl;

import younghan.core.discount.DiscountPolicy;
import younghan.core.member.Member;
import younghan.core.member.MemberRepository;
import younghan.core.order.Order;
import younghan.core.order.OrderService;

public class OrderServiceImpl implements OrderService {

    MemberRepository memberRepository;
    DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
