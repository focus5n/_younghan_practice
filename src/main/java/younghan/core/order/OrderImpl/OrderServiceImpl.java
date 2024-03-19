package younghan.core.order.OrderImpl;

import younghan.core.discount.DiscountPolicy;
import younghan.core.discount.discountImpl.RateDiscountPolicy;
import younghan.core.member.Member;
import younghan.core.member.MemberRepository;
import younghan.core.member.memberImpl.MemoryMemberRepository;
import younghan.core.order.Order;
import younghan.core.order.OrderService;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
