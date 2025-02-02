package yandexMusic.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class BeforeAllClosePopup {

    @BeforeAll
    public static void beforeAll(){
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        Configuration.browser = "chrome";
        //Configuration.browserVersion = "124.0";

        SelenideLogger.addListener("allure", new AllureSelenide());


        Configuration.pageLoadStrategy = "eager";

//        open("https://music.yandex.ru/");
//        refresh();
//        set language
//        $(".d-lang-switcher").scrollIntoView(true).click();
//        $$(".deco-popup-menu__item").findBy(text("RU")).click();
    }


}

