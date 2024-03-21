package younghan.core.order.OrderImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import younghan.core.discount.DiscountPolicy;
import younghan.core.member.Member;
import younghan.core.member.MemberRepository;
import younghan.core.order.Order;
import younghan.core.order.OrderService;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
