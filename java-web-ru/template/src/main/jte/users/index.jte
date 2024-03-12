@import exercise.dto.users.UsersPage
@param UsersPage page

<html>
<head>Hexlet</head>
<body>
<h1>${page.getHeader()}</h1>
@if(page.getUsers().isEmpty())
    <p>No users</p>
@else
    @for(var user: page.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
            <p>${user.getEmail()}</p>
        </div>
    @endfor
@endif
</body>
</html>