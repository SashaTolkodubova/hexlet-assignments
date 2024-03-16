@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(content = @`
    @if(page.getError() != null)
        <h2>${page.getError()}</h2>
    @endif
    <form action="${NamedRoutes.loginPath()}" method="post">
        <input type="text" placeholder="Name:" name="name" value="${page.getName()}"/>
        <input type="text" placeholder="Password:" name="password"/>
        <input type="submit"/>
    </form>
`)