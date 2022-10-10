package blogapp.Controller;

import blogapp.DTO.PostDTO;
import blogapp.Exceptions.BlogException;
import blogapp.Model.Post;
import blogapp.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPost(){
        List<Post> allPost = postService.getAllPost();
        List<PostDTO> listPost =  allPost.stream().map(post ->
                modelMapper.map(post, PostDTO.class)
        ).collect(Collectors.toList());
        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int id){
        PostDTO post = modelMapper.map(postService.getPostById(id), PostDTO.class);
        return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@Valid @RequestBody PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }
    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDTO> updatePostById(@RequestBody @Valid PostDTO postDTO, @PathVariable int id){
        Post post = modelMapper.map(postDTO, Post.class);
        PostDTO postResponse= modelMapper.map(postService.updatePostById(post,id), PostDTO.class);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable int id){
        return new ResponseEntity<String>(postService.deletePostById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/posts/", params = {"pageNo", "pageSize"} )
    public ResponseEntity<List<PostDTO>> pageAndSort(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(required = false) String sortBy){
        List<PostDTO> postList = postService.pageAndSort(pageNo,pageSize,sortBy).stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
    }


}
