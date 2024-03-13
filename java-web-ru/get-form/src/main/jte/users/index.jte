@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(content = @`
    <form action="/users" method="get">
        <input type="search" name="term" value="${page.getTerm()}">
        <input type="submit" value="search">
    </form>
    @if(page.getUsersList() != null)
        <h1>Users</h1>
        <table class="table table-striped">
            @for(var user: page.getUsersList())
                <tr>
                    <td>${user.getFirstName()} ${user.getLastName()}</td>
                    <td>${user.getEmail()}</td>
                </tr>
            @endfor
        </table>
    @endif
`)
