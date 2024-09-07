package gitshop.gitjpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;


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

    // unizque = true DB 중복값 X
    @Column(unique = true, length = 50)
    private String email;

    @Column(length = 200)
    private String password;

    @Column(unique = true, length = 50)
    private String name;

    @Embedded
    private Address address;    // 주소

    @Enumerated(EnumType.STRING)
    private MemberRole role;    // 회원 권한

    @Enumerated(EnumType.STRING)
    private MemberStatus status;    // 회원 상태

    private LocalDateTime registerDate;     // 등록 날짜

    // 사용자가 작성한 게시물 목록 (일대다 관계)
        // mappedBy 연관관계의 주인(외래키를 가지고 사용하는 테이블)
        // cascade 영속성 전이하는데 사용. 자식 엔티티에게 동일한 작업을 전이할지 결정
        // orphanRemoval 고아객체제거. 부모 엔티티와 연관관계가 끊어진 객체를 삭제할지 결정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    // 사용자가 작성한 댓글 목록 (일대다 관계)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /* ----- 미구현 ------ */
    // 사용자가 등록한 아이템
    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    // 기본 생성자
    protected Member() {}

    // 생성자
    public Member(String email, String password, String name, Address address, MemberRole role, MemberStatus status) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.role = role;
        this.status = status;
        this.registerDate = LocalDateTime.now();
    }
}
