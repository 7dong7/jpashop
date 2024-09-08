package gitshop.gitjpashop.repository;

import gitshop.gitjpashop.config.QueryDSLConfig;
import gitshop.gitjpashop.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
// 멤버 레포지토리

    private final EntityManager em;
    private final QueryDSLConfig query;

    // 멤버 등록 - 1명
    public void save(Member member){
        em.persist(member);
    }

    // 멤버 조회 id - 1명
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    // 멤버 리스트 조회 - n명
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 멤버 이름 조회 - n명
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    // 이메일 회원 조회 - 1명
    public Optional<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList().stream().findAny();
    }

}
