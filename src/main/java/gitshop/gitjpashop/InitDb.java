package gitshop.gitjpashop;

import gitshop.gitjpashop.domain.Address;
import gitshop.gitjpashop.domain.Member;
import gitshop.gitjpashop.domain.MemberRole;
import gitshop.gitjpashop.domain.MemberStatus;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbAdminInit();  // 관리자 등록
        initService.dbUserInit1();  // 회원1 등록
        initService.dbUserInit2();  // 회원2 등록
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        // 관리자
        public void dbAdminInit() {
            Address address = new Address("서울", "서대문구", "23412");
            Member member = new Member("admin@naver.com", "qwerqwer","관리자",
                    address, MemberRole.ADMIN, MemberStatus.ING);
            em.persist(member);
        }

        // 회원1
        public void dbUserInit1() {

            // 회원1 등록
            Address address = new Address("서울", "은평", "55555");
            Member member = new Member("enpung@gmail.com", "qwerqwer","은공",
                    address, MemberRole.USER, MemberStatus.ING);
            em.persist(member);
        }

        // 회원2
        public void dbUserInit2() {

            // 회원2 등록
            Address address = new Address("서울", "종로", "25132");
            Member member = new Member("jong@gmail.com", "qwerqwer","종룡",
                    address, MemberRole.USER, MemberStatus.ING);
            em.persist(member);
        }
    }
}
