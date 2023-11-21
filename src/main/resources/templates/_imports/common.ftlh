[#ftl]

[#macro page]
    <!DOCTYPE html>
    <html lang="cs">
    <head>
        <meta charset="UTF-8">
        <title>Business Cards</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" type="image/png" href="/images/favicon.png">
    </head>
    <body>
    [#nested]
    </body>
    </html>
[/#macro]

[#macro back]
    <nav>
        <a href="/">🡨 Back to the list</a>
    </nav>
[/#macro]

[#macro delete businessCardId]
        <form method="post" action="/delete">
            <input type="hidden" name="id" value="${businessCardId}" />
            <button class="button-like-link" type="submit">⌫ Delete</button>
        </form>
[/#macro]