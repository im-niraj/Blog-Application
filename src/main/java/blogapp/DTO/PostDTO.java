package blogapp.DTO;

import blogapp.Model.Comment;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostDTO {
    private int post_id;
    private String title;
    private String description;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private List<Comment> comments = new ArrayList<>();
}
