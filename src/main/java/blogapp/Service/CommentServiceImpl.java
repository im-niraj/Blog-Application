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
        return postRepo.findById(id).get().getComments();
    }

    @Override
    public Comment getCommentByIdBelongToPost(int postId, int commentId) {
        Optional<Post> opt = postRepo.findById(postId);
        if(opt.isPresent()){
            if(opt.get().getComments().size()>0){
              return commentRepo.findById(commentId).get();
            }
        }
        return  null;
    }

    @Override
    public String addCommentToPost(int postId, Comment comment) {
        Optional<Post> opt = postRepo.findById(postId);
        if(opt.isPresent()){
            comment.setPost(opt.get());
            opt.get().getComments().add(commentRepo.save(comment));
            postRepo.save(opt.get());
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
                opt1.get().setComment(comment.getComment());
                return commentRepo.save(opt1.get());
            }
        }
        return null;
    }

    @Override
    public String deleteCommentByIdBelongToPost(int postId, int commentId) {
        Optional<Post> opt = postRepo.findById(postId);
        if(opt.isPresent()){
            if (opt.get().getComments().size()>0){
                Optional<Comment> optCom = commentRepo.findById(commentId);
                if(optCom.isPresent()){
                    commentRepo.deleteById(commentId);
                    opt.get().getComments().remove(optCom.get());
                    postRepo.save(opt.get());
                    return "Comment deleted successfully..";
                }

                return "Comment id is incorrect";
            }
            return "This post does not have any comments";
        }
        return "Post id is not correct";
    }
}
