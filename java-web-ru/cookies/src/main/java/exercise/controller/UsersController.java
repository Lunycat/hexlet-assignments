package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    public static void create(Context ctx) {
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = Security.encrypt(ctx.formParam("password"));
        String token = Security.generateToken();

        User user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        Long id = UserRepository.search(firstName).get(0).getId();

        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(id));
    }

    public static void show(Context ctx) {
        String token = ctx.cookie("token");
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        User user = UserRepository.find(id).orElseThrow(() -> new NotFoundResponse("Not found"));
        UserPage page = new UserPage(user);

        if (user.getToken().equals(token)) {
            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
}
