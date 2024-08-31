package gitshop.gitjpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class Member {
    /**
     *  @Id : 기본키 (프라이머리키) 지정
     *
     *  @GeneratedValue : 기본키의 시퀀스 기능을 담담 점점 증가시킨다.
     *  GenerationType.IDENTITY : insert 쿼리 실행
     *  GenerationType.AUTO (default): insert 안함
     */

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, length = 50)
    private String email;
    private String password;

    @Column(unique = true, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Embedded
    private Address address;


    // 기본 생성자
    protected Member() {}

    // 생성자
    public Member(String email, String password, String name, Address address, MemberRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.role = role;
    }
}
