@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(content = @`
    @for(var post : page.getPosts())
        <p><a href="${NamedRoutes.post(post.getId())}">${post.getName()}</a></p>
    @endfor
    <p><a href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() - 1}">Назад</a>
        <a href="${NamedRoutes.postsPath()}?page=${page.getPageNumber() + 1}">Вперёд</a></p>
`)