package gitshop.gitjpashop.controller;

import gitshop.gitjpashop.domain.Post;
import gitshop.gitjpashop.dto.PostListDto;
import gitshop.gitjpashop.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public String post(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page) {
        /* ------------ 페이지 처리 없는 게시글 전부 가져오기 ------------ */
        List<Post> posts = postService.getPostList(); // 등록된 게시글 가져오기
        // 원하는 포맷으로 DateTimeFormatter 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 가져온 게시글 Dto로 변환
        List<PostListDto> postList = posts.stream()
                .map(p -> new PostListDto(
                        p.getId(),
                        p.getCreatedDate().format(formatter),
                        p.getMember().getName(),
                        p.getTitle(),
                        p.getViewCount()
                        ))
                .collect(Collectors.toList());

        model.addAttribute("postList", postList);

        /* ------------ 페이징 처리해 게시글 가져오기 ------------ */
        Page<Post> paging = postService.getList(page);
        model.addAttribute("page", paging);
        return "post/post";
    }

}
