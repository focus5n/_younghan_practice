package younghan.core.member;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import younghan.core.config.AppConfig;

public class MemberMain {

    public static void main(String[] args) {

        // MemberServiceTest 참조
        //        AppConfig appConfig = new AppConfig();
        //        MemberService memberService = appConfig.memberService();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = context.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);

        memberService.join(memberA);
        Member findMember = memberService.findMemberById(1L);

        System.out.println("DEFAULT MEMBER : " + memberA.getName());
        System.out.println("FIND MEMBER : " + findMember.getName());
        System.out.println("IS THIS SAME? " + memberA.equals(findMember));
    }
}
