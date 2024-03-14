@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page


@template.layout.page(content = @`
    <div>
        <h1>All posts</h1>
        <table class="table table-striped">
            @for(var post: page.getPosts())
                <tr>
                    <td><h2><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></h2></td>
                </tr>
            @endfor
        </table>
        <a href="${NamedRoutes.postsPath(page.getPageNumber() - 1)}" class="btn btn-primary">Back</a>
        <a href="${NamedRoutes.postsPath(page.getPageNumber() + 1)}" class="btn btn-primary">Next</a>
    </div>
`)