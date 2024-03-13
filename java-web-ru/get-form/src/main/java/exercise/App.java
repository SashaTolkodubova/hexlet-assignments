package exercise;

import io.javalin.Javalin;

import java.util.List;

import exercise.model.User;
import exercise.dto.users.UsersPage;

import java.util.Collections;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            List<User> usersListResylt;
            var term = ctx.queryParam("term");
            if (term != null) {
                usersListResylt = USERS.stream()
                        .filter(x -> x.getFirstName()
                                .toLowerCase()
                                .startsWith(term.toLowerCase()))
                        .toList();
            } else {
                usersListResylt = USERS;
            }
            var page = new UsersPage(usersListResylt, term);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
