package younghan.core.member.memberImpl;

import younghan.core.member.Member;
import younghan.core.member.MemberRepository;
import younghan.core.member.MemberService;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
