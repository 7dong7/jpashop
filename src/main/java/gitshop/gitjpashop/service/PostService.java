package gitshop.gitjpashop.service;

import gitshop.gitjpashop.domain.Post;
import gitshop.gitjpashop.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    // 전체 게시글 가져오기 여러개
    public List<Post> getPostList() {
        return postRepository.findAll();
    }


    // 페이징 처리한 게시글 가져오기
    public Page<Post> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return postRepository.findAll(pageable);
    }
}
