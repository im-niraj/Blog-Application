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

    @GetMapping(value = "/posts/", params = {"pageNo", "pageSize"} )
    public ResponseEntity<List<Post>> pageAndSort(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(required = false) String sortBy){
        return new ResponseEntity<List<Post>>(postService.pageAndSort(pageNo,pageSize,sortBy), HttpStatus.OK);
    }


}
