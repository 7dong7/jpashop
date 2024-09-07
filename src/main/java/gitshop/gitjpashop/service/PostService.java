package gitshop.gitjpashop.service;

import gitshop.gitjpashop.domain.Post;
import gitshop.gitjpashop.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    // 게시판 게시글 가져오기 여러개
    public List<Post> getPostList() {
        return postRepository.getPostList();
    }
}
