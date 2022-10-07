package blogapp.DTO;

import blogapp.Model.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private int id;
    private String comment;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private Post post;
}
