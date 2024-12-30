@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

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
    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <form action="/articles" method="post">
        <label>
            Название статьи
            <input type="text" name="title" required value="${page.getTitle()}">
        </label>
        <label>
            Описание
            <textarea name="content" required>${page.getContent()}</textarea>
        </label>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>