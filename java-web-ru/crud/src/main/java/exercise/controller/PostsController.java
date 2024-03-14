package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        var pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var posts = PostRepository.getEntities();
        PostsPage page;

        if (pageNumber < 1) {
            pageNumber = 1;
        } else if (pageNumber > ((posts.size() / 5) + 1)) { //size = 36, pageNamber = 7
            pageNumber = pageNumber - 1;
        }
        var offset = (pageNumber - 1) * 5;
        var offsetLast = offset + 5;
        if (posts.isEmpty()) {
            page = new PostsPage(posts, pageNumber);
        } else {
            if (offsetLast >= posts.size()) {
                offsetLast = posts.size() - 1;
            }
            var sliceOfPosts = posts.subList(offset, offsetLast);
            page = new PostsPage(sliceOfPosts, pageNumber);
        }

        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
    // END
}
