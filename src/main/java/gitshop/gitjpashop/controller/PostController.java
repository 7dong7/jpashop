package gitshop.gitjpashop.controller;

import gitshop.gitjpashop.domain.Post;
import gitshop.gitjpashop.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public String post(Model model) {
        List<Post> posts = postService.getPostList();


        return "post/post";
    }

}
