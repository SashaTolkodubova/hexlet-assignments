@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(content = @`
    @if(page.getName() == null)
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @else
        <div>Name: ${String.valueOf(page.getName())}</div>
        <a href="${NamedRoutes.logoutPath()}">Выйти</a>
    @endif
`)