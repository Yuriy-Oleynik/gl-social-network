package glsocialnetwork.repository;

import glsocialnetwork.model.Community;
import glsocialnetwork.model.Post;
import glsocialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCommunity(Community community);
    List<Post> findByUser(User user);
}
