package blogapp.Repo;

import blogapp.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    @Query(value = "SELECT * FROM comment c WHERE c.post_id = ?1", nativeQuery = true)
    List<Comment> getAllCommentByPostId(Integer post_id);

    @Query(value = "SELECT * FROM comment c where c.id = (SELECT c.id FROM comment c where c.post_id=?1 and id = ?2)", nativeQuery = true)
    Comment getCommentByIdBelongToPost(int postId, int commentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comment c where c.post_id=?1", nativeQuery = true)
    public void deleteByPostId(int postId);
}
