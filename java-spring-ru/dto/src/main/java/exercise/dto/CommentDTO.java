package exercise.dto;

import exercise.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private long id;
    private String body;

    public CommentDTO(Comment comment) {
        id = comment.getId();
        body = comment.getBody();
    }
}
