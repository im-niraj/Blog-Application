package blogapp.Service;

import blogapp.Exceptions.BlogException;
import blogapp.Model.Comment;
import blogapp.Model.Post;
import blogapp.Repo.CommentRepo;
import blogapp.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
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
    public Comment getCommentByIdBelongToPost(int postId, int commentId) throws BlogException{
        Optional<Post> opt = postRepo.findById(postId);
        if(opt.isPresent()){
            if(opt.get().getComments().size()>0){
                Optional<Comment> opt1 = commentRepo.findById(commentId);
                if(opt1.isPresent()){
                    return opt1.get();
                }
                throw new BlogException("Comment id is invalid");
            }
            throw new BlogException("No comment available for this post");
        }
        throw new BlogException("Post id is not correct");
    }

    @Override
    public String addCommentToPost(int postId, Comment comment) throws BlogException{
        Optional<Post> opt = postRepo.findById(postId);
        if(opt.isPresent()){
            comment.setPost(opt.get());
            opt.get().getComments().add(commentRepo.save(comment));
            postRepo.save(opt.get());
            return "Comment added to the Post";
        }
       throw  new BlogException("Post Id is incorrect");
    }

    @Override
    public Comment updateCommentByIdBelongToPost(int postId, int commentId, Comment comment) throws BlogException{
        Optional<Post> opt = postRepo.findById(postId);
        if(opt.isPresent()){
            Optional<Comment> opt1 = commentRepo.findById(commentId);
            if (opt1.isPresent()){
                opt1.get().setComment(comment.getComment());
                return commentRepo.save(opt1.get());
            }
            throw new BlogException("No comment available for this id "+commentId);
        }
       throw new BlogException("Post id is not correct");
    }


    @Transactional
    @Override
    public String deleteCommentByIdBelongToPost(int postId, int commentId) throws BlogException {
       Optional<Post> optPost = postRepo.findById(postId);
       if(optPost.isPresent()){
           Iterator itr = optPost.get().getComments().iterator();
           while (itr.hasNext()){
               Comment c = (Comment) itr.next();
               if(c.getId() == commentId){
                   itr.remove();
                   postRepo.save(optPost.get());
                   commentRepo.deleteById(commentId);
                   return "Comment deleted successfully..";
               }
           }
           if(optPost.get().getComments().size()>0){
               throw new BlogException("No comment available for this comment id "+commentId);
           }
           throw new BlogException("No comment available");
       }
       throw new BlogException("Post id is incorrect!");
    }
}
