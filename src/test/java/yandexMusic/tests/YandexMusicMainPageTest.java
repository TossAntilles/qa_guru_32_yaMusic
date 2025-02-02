package yandexMusic.tests;


import static io.qameta.allure.Allure.attachment;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import yandexMusic.components.YandexMusicMainPageComponents;
import yandexMusic.data.Language;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Tag("musicMain")
public class YandexMusicMainPageTest extends BeforeAllClosePopup {

    YandexMusicMainPageComponents yaMusic = new YandexMusicMainPageComponents();

    @BeforeEach
    public void beforeEach(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу Яндекс.Музыки", () -> {
            yaMusic.openPage();
        });

    }

    @AfterEach
    public void afterEach(){
        clearBrowserCookies();
    }


    @Feature("Яндекс.Музыка")
    @Issue("Проверка главной страницы Яндекс.Музыки")
    @Story("Проверка имени блоков на главной")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Yandex.Music", url = "https://music.yandex.ru")
    @DisplayName("Проверка комбинации имени блока через список @ValueSource.")
    @ParameterizedTest(name = "На странице музыки проверяем наличие элемента {0}")
    @Tags({
            @Tag("NORMAL"),
            @Tag(("Web"))
    })
    @ValueSource(strings = {
            "Подкасты и книги",
            "Новые релизы",
            "Популярные плейлисты",
            "Чарт",
            "Подборки"
    })
    void checkPageCouruselsAndList(String elementName){
        step("Проверяем хэдер страницы", () -> {
            yaMusic.checkBlockHeader(elementName);
            attachment("Source", webdriver().driver().source());
        });
    }

    @Feature("Яндекс.Музыка")
    @Issue("Проверка главной страницы Яндекс.Музыки")
    @Story("Проверка имени блоков на главной")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Yandex.Music", url = "https://music.yandex.ru")
    @DisplayName("Проверка комбинации имени и подзаголовка блока через @CsvSource.")
    @ParameterizedTest(name = "На странице музыки проверяем наличие элемента {0}")
    @Tags({
            @Tag("NORMAL"),
            @Tag(("Web"))
    })
    @CsvSource (value = {
            "Подкасты и книги | Слушайте не только музыку",
            "Новые релизы | Новые треки, альбомы и сборники",
            "Популярные плейлисты | Собрано для вас нашей редакцией",
            "Чарт | Треки, популярные на Яндекс Музыке прямо сейчас",
            "Подборки | Плейлисты, подобранные под настроение или по жанру"
    }, delimiter = '|')
    void checkPageCouruselsAndListSubtext(String elementName, String substring){
        step("Проверяем заголовки и подзаголовки в блоках на странице", () -> {
            yaMusic.checkSubtitle(elementName, substring);
            attachment("Source", webdriver().driver().source());
        });
    }

    @Feature("Яндекс.Музыка")
    @Issue("Проверка сабкатегории Яндекс.Музыки")
    @Story("Проверка имени блоков на сабстранице")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Yandex.Music", url = "https://music.yandex.ru")
    @DisplayName("Проверка комбинации имени и подзаголовка блока через @CsvFileSource.")
    @ParameterizedTest(name = "На странице музыки проверяем наличие элемента {0}")
    @Tags({
            @Tag("NORMAL"),
            @Tag(("Web"))
    })
    @CsvFileSource(resources = "/testData/musicBlocks.csv", delimiter = '|')
    void checkPageCouruselsAndListSubtextFromCsv(String elementName, String substring){
        step("Переходис в сабкатегорию Подкасты и Книги", () -> {
            yaMusic.openCategory("Подкасты и книги");
        });
        step("Проверяем заголовки и подзаголовки в блоках на странице", () -> {
            yaMusic.checkSubtitle(elementName, substring);
            attachment("Source", webdriver().driver().source());
        });
    }

    @Feature("Яндекс.Музыка")
    @Issue("Проверка главной страницы Яндекс.Музыки")
    @Story("Проверка выбора языка")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Yandex.Music", url = "https://music.yandex.ru")
    @DisplayName("Проверяем изменение языка в футере страницы")
    @ParameterizedTest(name = "Выбираем язык на {0}, проверяем заголовок и меню.")
    @Tag("NORMAL")
    @EnumSource(Language.class)
    void changePageLanguage(Language language){
        step("Меняем язык страницы", () -> {
            yaMusic.selectLanguage(language);
            attachment("Source", webdriver().driver().source());
        });
        step("Проверяем языв заголовка страницы", () -> {
            yaMusic.checkHeader(language);
            attachment("Source", webdriver().driver().source());
        });
    }


}
