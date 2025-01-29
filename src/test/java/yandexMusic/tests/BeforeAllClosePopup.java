package yandexMusic.tests;

import com.codeborne.selenide.Configuration;
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

        //set language
        $(".d-lang-switcher").scrollIntoView(true).click();
        $$(".deco-popup-menu__item").findBy(text("RU")).click();
    }


}

