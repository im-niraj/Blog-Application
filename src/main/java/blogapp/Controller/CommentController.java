package blogapp.Controller;

import blogapp.DTO.CommentDTO;
import blogapp.Model.Comment;
import blogapp.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getAllCommentOfPostById(@PathVariable int id){
        List<CommentDTO> commentList = commentService.getAllCommentOfPostById(id).stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<List<CommentDTO>>(commentList, HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<String> addCommentToPost(@PathVariable int postId, @RequestBody @Valid CommentDTO commentDTO){
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        return new ResponseEntity<String>(commentService.addCommentToPost(postId, comment), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentByIdBelongToPost(@PathVariable int postId, @PathVariable int commentId){
        CommentDTO commentDTO = modelMapper.map(commentService.getCommentByIdBelongToPost(postId, commentId), CommentDTO.class);
        return new ResponseEntity<CommentDTO>(commentDTO, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateCommentByIdBelongToPost(@PathVariable int postId, @PathVariable int commentId, @RequestBody @Valid CommentDTO commentDTO){
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        CommentDTO commentResponse = modelMapper.map(commentService.updateCommentByIdBelongToPost(postId, commentId, comment), CommentDTO.class);
        return new ResponseEntity<CommentDTO>(commentResponse, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByIdBelongToPost(@PathVariable int postId, @PathVariable int commentId){
        return new ResponseEntity<String>(commentService.deleteCommentByIdBelongToPost(postId,commentId), HttpStatus.OK);
    }
}
