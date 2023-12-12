package dasanda.BE.service.member;

import dasanda.BE.domain.Member;
import dasanda.BE.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 저장
    @Transactional
    public Member join(Member member){
        memberRepository.save(member);
        return member;
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 단일 회원 조회
    public Member findMemberById(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
