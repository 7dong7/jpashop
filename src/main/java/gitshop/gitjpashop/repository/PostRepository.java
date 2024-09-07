package gitshop.gitjpashop.repository;


import gitshop.gitjpashop.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public List<Post> getPostList() {

        return em.createQuery("select p from Post p order by p.id desc", Post.class)
                .getResultList();
    }
}
