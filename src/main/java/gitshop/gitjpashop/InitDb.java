package gitshop.gitjpashop;

import gitshop.gitjpashop.domain.*;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbAdminInit();  // 관리자 등록
        initService.dbMemberInit1();  // 회원1
        initService.dbMemberInit2();  // 회원2
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;

        // 관리자
        public void dbAdminInit() {
            Address address = new Address("서울", "서대문구", "23412");
            String encodePassword = passwordEncoder.encode("qwerqwer");
            Member member = new Member("admin@naver.com", encodePassword,"관리자",
                    address, MemberRole.ADMIN, MemberStatus.ING);
            em.persist(member);
        }

        // 회원1
        public void dbMemberInit1() {

            // 회원1 등록
            Address address = new Address("서울", "은평", "55555");
            Member member1 = createMember("enpung@gmail.com", "qwerqwer", "은공", address);
            em.persist(member1);
            
            // 회원1 상품 등록
            Item item1 = new Item("조선 간장 1.5L", 7800, 1000, member1);
            em.persist(item1);
            Item item2 = new Item("솜사탕 50g", 2500, 200, member1);
            em.persist(item2);
        }

        // 회원2
        public void dbMemberInit2() {

            // 회원2 등록
            Address address = new Address("서울", "종로", "25132");
            Member member2 = createMember("jong@gmail.com", "qwerqwer", "종룡", address);
            em.persist(member2);

            // 회원1 상품 등록
            Item item1 = new Item("왕뚜껑", 2000, 330, member2);
            em.persist(item1);
            Item item2 = new Item("빨대 200개입", 8000, 89, member2);
            em.persist(item2);
        }

        // 회원 생성자
        private Member createMember(String email, String password, String name, Address address) {
            String encodePassword = passwordEncoder.encode(password);
            Member member = new Member(email, encodePassword, name, address, MemberRole.USER, MemberStatus.ING);
            return member;
        }
    }
}
