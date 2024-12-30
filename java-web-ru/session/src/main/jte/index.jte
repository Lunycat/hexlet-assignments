@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(content = @`
    @if(page.getName() == null)
        <form action="/sessions/build" method="get">
            <button type="submit">Войти</button>
        </form>
    @else
        <form action="/sessions/delete" method="post">
            ${(String) page.getName()}
            <button type="submit">Выйти</button>
        </form>
    @endif
    `)
