package yandexMusic.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class BeforeAllClosePopup {

    @BeforeAll
    public static void beforeAll(){
        Configuration.pageLoadStrategy = "eager";
        open("https://music.yandex.ru/");
        refresh();

        SelenideLogger.addListener("allure", new AllureSelenide());

        //set language
        $(".d-lang-switcher").scrollIntoView(true).click();
        $$(".deco-popup-menu__item").findBy(text("RU")).click();
    }


}