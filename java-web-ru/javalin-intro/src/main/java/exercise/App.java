package exercise;

// BEGIN
import io.javalin.Javalin;
// END

public final class App {

    public static Javalin getApp() {
        Javalin app = Javalin.create(c -> c.plugins.enableDevLogging());
        app.get("/", context -> context.result("Hello World!"));
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
