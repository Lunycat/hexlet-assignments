package exercise.dto;

import java.util.List;

import exercise.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDTO {

    private long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;

    public PostDTO(Post post) {
        id = post.getId();
        title = post.getTitle();
        body = post.getBody();
    }
}
