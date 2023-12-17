package dasanda.BE.repository.member;

import dasanda.BE.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // JPA 활용 Entity 관리
    private final EntityManager em;

    // 회원 저장
    // 추후 인증 추가 및 QueryDSL 활용
    public void save(Member member){
        em.persist(member);
    }

    // 단일 멤버 조회
    public Member findByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getSingleResult();
    }
    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    // 전체 멤버 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
