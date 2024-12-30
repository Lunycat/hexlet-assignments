@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(content = @`
        <h1><a href="/">Главная страница сайта</a></h1>
        @for(var user : page.getUsers())
            <p><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></p>
        @endfor
    `
)