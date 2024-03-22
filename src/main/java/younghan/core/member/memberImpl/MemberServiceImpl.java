package younghan.core.member.memberImpl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import younghan.core.member.Member;
import younghan.core.member.MemberRepository;
import younghan.core.member.MemberService;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(@Qualifier("memberRepository") MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // For Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
