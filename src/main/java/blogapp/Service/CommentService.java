package blogapp.Service;


import blogapp.Model.Comment;
import blogapp.Model.Post;

import java.util.List;

public interface CommentService {
    public List<Comment> getAllCommentOfPostById(int id);
    public Comment getCommentByIdBelongToPost(int postId, int commentId);
    public String addCommentToPost(int postId, Comment comment);
    public Comment updateCommentByIdBelongToPost(int postId, int commentId, Comment comment);
    public String deleteCommentByIdBelongToPost(int postId, int commentId);
}
