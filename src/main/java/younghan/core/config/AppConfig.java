package younghan.core.config;

import younghan.core.discount.DiscountPolicy;
import younghan.core.discount.discountImpl.FixDiscountPolicy;
import younghan.core.member.MemberService;
import younghan.core.member.memberImpl.MemberServiceImpl;
import younghan.core.member.memberImpl.MemoryMemberRepository;
import younghan.core.order.OrderImpl.OrderServiceImpl;
import younghan.core.order.OrderService;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
