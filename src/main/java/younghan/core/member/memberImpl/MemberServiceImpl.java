package younghan.core.member.memberImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import younghan.core.member.Member;
import younghan.core.member.MemberRepository;
import younghan.core.member.MemberService;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

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
