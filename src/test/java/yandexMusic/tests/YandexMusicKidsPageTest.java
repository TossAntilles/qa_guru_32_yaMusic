package yandexMusic.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import yandexMusic.components.YandexMusicKidsPageSteps;

import static com.codeborne.selenide.Selenide.*;


public class YandexMusicKidsPageTest extends BeforeAllClosePopup {

    YandexMusicKidsPageSteps steps = new YandexMusicKidsPageSteps();

    @BeforeEach
    public void beforeEach(){
        steps.openPage();
    }

    @AfterEach
    public void afterEach(){
        Attach.screenshotAs("Last Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        clearBrowserCookies();
    }

    @Tag("musicKids")
    @Test
    @Feature("Яндекс.Музыка Kids")
    @Issue("Проверка детской страницы Яндекс.Музыки")
    @Story("Проверка элементов в хэдере")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Yandex.Music Kids", url = "https://music.yandex.ru/kids")
    @DisplayName("Проверка ссылок в хэдере детской Яндекс.Музыки.")
    @Tags({
            @Tag("NORMAL"),
            @Tag(("Web"))
    })
    void checkPageHeader(){
        steps.checkHeaderLogo();
        steps.checkHeaderMenu();
        steps.checkHeaderPromo();
        steps.checkHeaderLogin();
    }

    @Tag("musicKids")
    @Test
    @Feature("Яндекс.Музыка Kids")
    @Issue("Проверка детской страницы Яндекс.Музыки")
    @Story("Проверка каруселей на странице")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Yandex.Music Kids", url = "https://music.yandex.ru/kids")
    @DisplayName("Проверка блоков на странице детской Яндекс Музыки.")
    @Tags({
            @Tag("NORMAL"),
            @Tag(("Web"))
    })
    void checkPageCouruselsAndList(){
        steps.checkCouruselsAndList("Зимние новинки");
        steps.checkCouruselsAndList("Что послушаем?");
        steps.checkCouruselsAndList("В гостях у бабушки с дедушкой");
        steps.checkCouruselsAndList("Зимние истории");
        steps.checkCouruselsAndList("Только в Яндекс Музыке");
    }

    @Tag("musicKids")
    @Feature("Яндекс.Музыка Kids")
    @Issue("Проверка детской страницы Яндекс.Музыки")
    @Story("Проверка выбора языка")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Yandex.Music", url = "https://music.yandex.ru")
    @DisplayName("Проверяем изменение языка в футере страницы")
    @Tag("NORMAL")
    @ParameterizedTest(name = "Проверяем выбор язука {0} в футере")
    @CsvSource(value = {
            "EN,For Kids",
            "RU, Детям",
            "UK,Детям",
    })
    void changePageLanguage(String language, String title){
        steps.changeLanguage("RU");
        steps.checkPageTitle("Детям");
    }
}
