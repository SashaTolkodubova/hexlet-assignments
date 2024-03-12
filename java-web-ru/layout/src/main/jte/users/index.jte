@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    @for(var user: page.getUsers())
        <a href="/users/${user.getId()}">Name: ${user.getFirstName()} ${user.getLastName()}</a>
        <p>email: ${user.getEmail()} </p>
    @endfor
`
)