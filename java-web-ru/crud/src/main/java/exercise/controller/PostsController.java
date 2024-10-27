package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class PostsController {

    public static void index(Context ctx) {
        int pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        if (pageNumber == 0) pageNumber = 1;

        List<Post> posts = PostRepository.findAll(pageNumber, 5);
        if (posts.isEmpty()) {
            pageNumber -= 1;
            posts = PostRepository.findAll(pageNumber, 5);
        }

        PostsPage page = new PostsPage(posts, pageNumber);
        ctx.render("posts/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        long id = ctx.pathParamAsClass("id", Long.class).get();
        Post post = PostRepository.find(id).orElseThrow(() -> new NotFoundResponse("Page not found"));
        PostPage page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
