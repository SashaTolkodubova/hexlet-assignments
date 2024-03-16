package exercise.controller;

import java.util.Collections;

import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;

import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

public class SessionsController {

    // BEGIN
    public static void rootPath(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(UsersRepository::existsByName, "Wrong username or password.")
                    .get();
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> UsersRepository
                            .findByName(name)
                            .getPassword()
                            .equals(Security.encrypt(value)), "Wrong username or password.")
                    .get();
            ctx.sessionAttribute("currentUser", name);
            ctx.redirect(NamedRoutes.rootPath());
        } catch (ValidationException e) {
            var name = ctx.formParam("name");

            var page = new LoginPage(name, "Wrong username or password.");
            ctx.render("build.jte", Collections.singletonMap("page", page)).status(302);
        }


    }

    public static void delete(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
