package blogapp.Controller;

import blogapp.Model.Comment;
import blogapp.Model.Post;
import blogapp.Service.CommentService;
import blogapp.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPost(){
        return new ResponseEntity<List<Post>>(postService.getAllPost(), HttpStatus.OK) ;
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody Post post){
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePostById(@RequestBody Post post, @PathVariable int id){
        return new ResponseEntity<>(postService.updatePostById(post,id), HttpStatus.OK);
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable int id){
        return new ResponseEntity<String>(postService.deletePostById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/posts", params = {"pageNo", "pageSize", "sortBy"})
    public ResponseEntity<List<Post>> pageAndSort(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortBy){
        return new ResponseEntity<List<Post>>(postService.pageAndSort(pageNo,pageSize,sortBy), HttpStatus.OK);
    }

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
