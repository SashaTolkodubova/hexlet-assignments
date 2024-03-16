@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(page = page,
content = @`
    <h1>Posts:</h1>
    <table class="table table-striped">
        @for(var post : page.getPosts())
            <tr>
                <td>${post.getName()}</td>
                <td>${post.getBody()}</td>
            </tr>
        @endfor
    </table>
`)
