package exercise.controller.users;

import java.net.URI;
import java.net.URL;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> index(@PathVariable String id) {
                var result = posts.stream()
                .filter(p -> p.getUserId() == Integer.valueOf(id))
                .toList();
        return result;
    }

@ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{id}/posts")
    public Post create(@PathVariable String id, @RequestBody Post data) {
        Post post = new Post();
        post.setBody(data.getBody());
        post.setTitle(data.getTitle());
        post.setSlug(data.getSlug());
        post.setUserId(Integer.valueOf(id));
       posts.add(post);
        return post;
    }

}
// END
