package gitshop.gitjpashop.repository;


import gitshop.gitjpashop.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryOld {

    private final EntityManager em;

    public Page<Post> findAll(Pageable pageable) {
        // JPQL 쿼리 작성
        TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p ORDER BY p.id DESC", Post.class);

        // 페이지 정보를 사용하여 결과 제한
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // 페이징 처리된 결과 목록을 가져옴
        List<Post> posts = query.getResultList();

        // 전체 게시글 수 계산
        long total = getTotalCount();

        // 페이지 객체로 반환
        return new PageImpl<>(posts, pageable, total);
    }

    // 전체 게시글 수를 가져오는 메서드
    private long getTotalCount() {
        TypedQuery<Long> countQuery = em.createQuery("SELECT COUNT(p) FROM Post p", Long.class);
        return countQuery.getSingleResult();
    }


    public List<Post> getPostList() {

        return em.createQuery("select p from Post p order by p.id desc", Post.class)
                .getResultList();
    }
}
