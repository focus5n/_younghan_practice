package younghan.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import younghan.core.discount.DiscountPolicy;
import younghan.core.discount.discountImpl.RateDiscountPolicy;
import younghan.core.member.MemberRepository;
import younghan.core.member.MemberService;
import younghan.core.member.memberImpl.MemberServiceImpl;
import younghan.core.member.memberImpl.MemoryMemberRepository;
import younghan.core.order.OrderImpl.OrderServiceImpl;
import younghan.core.order.OrderService;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("Call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }
}
