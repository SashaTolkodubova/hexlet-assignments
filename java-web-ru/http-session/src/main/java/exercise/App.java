package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var page = ctx.queryParam("page"); //page = 5;
            var per = ctx.queryParam("per"); //per = 3;
            List<Map<String, String>> users = Data.getUsers();
            List<Map<String, String>> result = new ArrayList<>();

            if ((page == null) || (per == null)) {
                for (int i = 0; i < 5; i++) {
                    result.add(users.get(i));
                }
            } else {
                var lastIndex = Integer.valueOf(page) * Integer.valueOf(per);
                var firstIndex = lastIndex - Integer.valueOf(per);
                for (int i = firstIndex; i < lastIndex; i++) {
                    result.add(users.get(i));
                }
            }
            ctx.json(result);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
