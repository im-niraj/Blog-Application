package blogapp.Service;

import blogapp.Model.Comment;
import blogapp.Model.Post;
import blogapp.Repo.CommentRepo;
import blogapp.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Override
    public List<Comment> getAllCommentOfPostById(int id) {
        return commentRepo.getAllCommentByPostId(id);
    }

    @Override
    public Comment getCommentByIdBelongToPost(int postId, int commentId) {
        return commentRepo.getCommentByIdBelongToPost(postId, commentId) ;
    }

    @Override
    public String addCommentToPost(int postId, Comment comment) {
        Optional<Post> opt = postRepo.findById(postId);
        if(opt.isPresent()){
            Comment comment1 = new Comment();
            comment1.setComment(comment.getComment());
            comment1.setPost_id(opt.get());
            commentRepo.save(comment1);
            return "Comment added to the Post";
        }
        return "Post id is not correct";
    }

    @Override
    public Comment updateCommentByIdBelongToPost(int postId, int commentId, Comment comment) {
        Optional<Post> opt = postRepo.findById(postId);
        if(opt.isPresent()){
            Optional<Comment> opt1 = commentRepo.findById(commentId);
            if (opt1.isPresent()){
                Comment comment1 =opt1.get();
                comment1.setComment(comment.getComment());
                commentRepo.save(comment1);
                return comment1;
            }
        }
        return null;
    }

    @Override
    public String deleteCommentByIdBelongToPost(int postId, int commentId) {
        Optional<Post> opt = postRepo.findById(postId);
        if(opt.isPresent()){
            Optional<Comment> opt1 = commentRepo.findById(commentId);
            if (opt1.isPresent()){
                commentRepo.deleteById(commentId);
                return "Comment deleted successfully..";
            }
            return "No comment found with id "+commentId;
        }
        return "Post id is not correct";
    }
}
