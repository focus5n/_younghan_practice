package younghan.core.member;

import younghan.core.member.memberImpl.MemberServiceImpl;

public class MemberMain {

    public static void main(String[] args) {

        // MemberServiceTest 참조
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);

        memberService.join(memberA);
        Member findMember = memberService.findMemberById(1L);

        System.out.println("DEFAULT MEMBER : " + memberA.getName());
        System.out.println("FIND MEMBER : " + findMember.getName());
        System.out.println("IS THIS SAME? " + memberA.equals(findMember));
    }
}
