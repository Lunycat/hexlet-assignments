package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<PostDTO> index() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + 100 + " not found"));

        PostDTO postDTO = new PostDTO(post);

        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDTO> commentsDTO = comments.stream()
                .map(CommentDTO::new)
                .toList();

        postDTO.setComments(commentsDTO);

        return postDTO;
    }

}
