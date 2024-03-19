package younghan.core.config;

import younghan.core.discount.DiscountPolicy;
import younghan.core.discount.discountImpl.RateDiscountPolicy;
import younghan.core.member.MemberRepository;
import younghan.core.member.MemberService;
import younghan.core.member.memberImpl.MemberServiceImpl;
import younghan.core.member.memberImpl.MemoryMemberRepository;
import younghan.core.order.OrderImpl.OrderServiceImpl;
import younghan.core.order.OrderService;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    private static MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
