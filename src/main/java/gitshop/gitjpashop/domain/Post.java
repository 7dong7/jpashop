package gitshop.gitjpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;  // 게시물 ID

    private String title;  // 게시물 제목
    private String content;  // 게시물 내용
    private LocalDateTime createdDate;  // 생성일
    private LocalDateTime modifiedDate;  // 수정일
    private int viewCount;  // 조회수

    // 게시물 작성자 (다대일 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 게시물에 달린 댓글 목록 (일대다 관계)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;


    public Post() {}

    public Post(String title, String content, Member member, int viewCount) {
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.member = member;
        this.viewCount = viewCount;
    }


}
