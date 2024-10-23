@import exercise.dto.users.UsersPage
@param UsersPage page

<main>
    @for (var user : page.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
        </div>
    @endfor
</main>

