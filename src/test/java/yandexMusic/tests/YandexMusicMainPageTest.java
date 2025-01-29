package yandexMusic.tests;



import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import yandexMusic.components.YandexMusicMainPageComponents;
import yandexMusic.data.Language;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class YandexMusicMainPageTest extends BeforeAllClosePopup {

    //1) Написать свои варианты параметризованных wеб-тестов (не на поиск в янексе или гугле);
    //1.1) Попробовать разные варианты датапровайдеров (аннотаций), минимум 3;
    //2) Если вы новичок в тесткейсах, то к каждому тесту написать отдельные тексткейсы в текстовых файлах

    YandexMusicMainPageComponents yaMusic = new YandexMusicMainPageComponents();

    @BeforeEach
    public void beforeEach(){
        yaMusic.openPage();
    }

    @AfterEach
    public void afterEach(){
        clearBrowserCookies();
    }

    @DisplayName("Проверка комбинации имени блока через список @ValueSource")
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
        yaMusic.checkBlockHeader(elementName);
    }


    @DisplayName("Проверка комбинации имени и подзаголовка блока через @CsvSource")
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
        yaMusic.checkSubtitle(elementName, substring);
    }


    @DisplayName("Проверка комбинации имени и подзаголовка блока через @CsvFileSource")
    @ParameterizedTest(name = "На странице музыки проверяем наличие элемента {0}")
    @Tags({
            @Tag("NORMAL"),
            @Tag(("Web"))
    })
    @CsvFileSource(resources = "/testData/musicBlocks.csv", delimiter = '|')
    void checkPageCouruselsAndListSubtextFromCsv(String elementName, String substring){
        yaMusic.opecCategory("Подкасты и книги");
        yaMusic.checkSubtitle(elementName, substring);
    }


    @DisplayName("Проверяем изменение языка на главной странице")
    @ParameterizedTest(name = "Выбираем язык на {0}, проверяем заголовок и меню")
    @Tag("NORMAL")
    @EnumSource(Language.class)
    void changePageLanguage(Language language){
        yaMusic.selectLanguage(language);
        yaMusic.checkHeader(language);
    }


}
