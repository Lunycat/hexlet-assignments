package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;
import io.javalin.http.Context;

public class SessionsController {

    public static void build(Context ctx) {
        LoginPage page = new LoginPage(null, null);
        ctx.render("build.jte",model("page", page));
    }

    public static void login(Context ctx) {
        String name = ctx.formParam("name");
        String password = encrypt(ctx.formParam("password"));
        User user = UsersRepository.findByName(name).orElse(new User("error", "error"));
        String passwordUser = user.getPassword();

        if (password.equals(passwordUser)) {
            ctx.sessionAttribute("currentUser", name);
            ctx.redirect("/");
        } else {
            LoginPage page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }

    public static void index(Context ctx) {
        MainPage page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", model("page", page));
    }
}
