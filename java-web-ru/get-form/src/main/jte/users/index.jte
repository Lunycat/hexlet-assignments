@import exercise.dto.users.UsersPage
@param UsersPage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <form action="/users" method="get">
        <label>
            <input type="search" required name="term">
            <input type="submit" value="Найти">
        </label>
    </form>

    @for(var user : page.getUsers())
        <p>${user.getFirstName()} ${user.getLastName()}</p>
    @endfor
</body>
</html>