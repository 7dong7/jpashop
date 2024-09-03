package gitshop.gitjpashop.repository;

import gitshop.gitjpashop.config.QueryDSLConfig;
import gitshop.gitjpashop.controller.form.LoginForm;
import gitshop.gitjpashop.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
// 멤버 레포지토리

    private final EntityManager em;
    private final QueryDSLConfig qf;

    // 멤버 등록 - 1명
    public void save(Member member){
        em.persist(member);
    }

    // 멤버 조회 - 1명
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
    
    // 멤버 로그인
    public Member findLoginMember(LoginForm form) {
        return em.createQuery("select m from Member m where m.email = :email and m.password = :password", Member.class)
                .setParameter("email", form.getEmail())
                .setParameter("password", form.getPassword())
                .getSingleResult();
    }
}
