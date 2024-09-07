package gitshop.gitjpashop.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostListDto {

    private Long post_id; // 게시글번호
    private String createdDate; // 작성날짜
    private String name; // 작성자이름
    private String title; // 제목
    private int viewCount; // 조회수

    public PostListDto(Long post_id, String createdDate, String name, String title, int viewCount) {
        this.post_id = post_id;
        this.createdDate = createdDate;
        this.name = name;
        this.title = title;
        this.viewCount = viewCount;
    }
}
