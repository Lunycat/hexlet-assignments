@import exercise.dto.posts.PostsPage

@param PostsPage page

@template.layout.page(
    page = page,
    content = @`
    @for(var post : page.getPosts())
        <p>${post.getName()}</p>
        <p>${post.getBody()}</p>
    @endfor
    `)
