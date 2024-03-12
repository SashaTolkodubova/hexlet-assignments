@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
        <p>Name: ${page.getUser().getFirstName()} ${page.getUser().getLastName()}</p>
        <p>email: ${page.getUser().getEmail()}</p>
`
)
