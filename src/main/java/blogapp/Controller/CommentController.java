package blogapp.Controller;

import blogapp.Model.Comment;
import blogapp.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;


    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<Comment>> getAllCommentOfPostById(@PathVariable int id){
        return new ResponseEntity<List<Comment>>(commentService.getAllCommentOfPostById(id), HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<String> addCommentToPost(@PathVariable int postId, @RequestBody Comment comment){
        return new ResponseEntity<String>(commentService.addCommentToPost(postId, comment), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> getCommentByIdBelongToPost(@PathVariable int postId, @PathVariable int commentId){
        return new ResponseEntity<Comment>(commentService.getCommentByIdBelongToPost(postId,commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> updateCommentByIdBelongToPost(@PathVariable int postId, @PathVariable int commentId, @RequestBody Comment comment){
        return new ResponseEntity<Comment>(commentService.updateCommentByIdBelongToPost(postId,commentId, comment), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByIdBelongToPost(@PathVariable int postId, @PathVariable int commentId){
        return new ResponseEntity<String>(commentService.deleteCommentByIdBelongToPost(postId,commentId), HttpStatus.OK);
    }
}
