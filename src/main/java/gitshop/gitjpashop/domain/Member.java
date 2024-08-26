package gitshop.gitjpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;

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
    private Long member_id;

    private String name;

    @Embedded
    private Address address;



}
