package yandexMusic.components;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class YandexMusicKidsPageSteps {


    @Step("Открываем страницу детской Яндекс Музыки")
    public void openPage(){
        open("https://music.yandex.ru/kids");
    }

    @Step("Проверяем ссылки в иконках Музыки в шапке")
    public void checkHeaderLogo(){
        $(".head-container").$(".head-kids__left").$(".d-logo__ya").shouldBe(visible);
        $(".head-container").$(".head-kids__left").$(".d-logo__ya-sub").shouldBe(visible);
    }
    @Step("Проверяем ссылки в навигации в шапке ")
    public void checkHeaderMenu(){
        $(".head-container").$(".head-kids__nav").$("[data-name=main]").shouldBe(visible);
        $(".head-container").$(".head-kids__nav").$("[data-name=non-music]").shouldBe(visible);
        $(".head-container").$(".head-kids__nav").$("[data-name=kids]").shouldBe(visible);
    }

    @Step("Проверяем наличие блока промо в шапке")
    public void checkHeaderPromo(){
        $(".head-container").$(".head-kids__promocode-link").$("[href*='/gift?serviceName=music&promoName=music_common']").shouldBe(visible);

    }

    @Step("Проверяем наличие кнопки логина в шапке")
    public void checkHeaderLogin(){
        $(".head-container").$(".head-kids__user").$("[href*='passport.yandex.ru/auth?origin=music_button-header']").shouldBe(visible);

    }

    @Step("Проверяем наличие блока {title} на странице")
    public void checkCouruselsAndList(String title){
        $(".centerblock").shouldHave(text(title));
    }

    @Step("Выбираем язык {language} в футере")
    public void changeLanguage(String language){
        $(".d-lang-switcher").scrollIntoView(true).click();
        $$(".deco-popup-menu__item").findBy(text(language)).click();
    }

    @Step("Проверяем язык заголовка: ({title}) ")
    public void checkPageTitle(String title){
        $(".d-header__title-text").shouldHave(text(title));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}