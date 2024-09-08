package gitshop.gitjpashop.repository;

import gitshop.gitjpashop.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 페이징 처리
    Page<Post> findAll(Pageable pageable);

}
