@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(content = @`
    @if(page.getError() != null)
        <p>${page.getError()}</p>
    @endif
    <form action="/sessions" method="post">
        <div>
            <label>
                Name
                <input type="text" name="name" value="${page.getName()}">
            </label>
        </div>
        <div>
            <label>
                Password
                <input type="password" name="password">
            </label>
        </div>
        <input type="submit">
    </form>
`)