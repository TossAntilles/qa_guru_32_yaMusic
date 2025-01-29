package yandexMusic.components;


//import yandexMusic.data.Language;

import com.codeborne.selenide.BaseElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.params.provider.EnumSource;
import yandexMusic.data.Language;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YandexMusicMainPageComponents {

    private static SelenideElement pageHeader = $(".d-header__title-text"),
        languageSwitcher = $(".d-lang-switcher"),
        blockHeader = $(".centerblock");
    private static BaseElementsCollection languageSwitcherSelector =  $$(".deco-popup-menu__item"),
        blockSubtitle = $$(".page-line__subtitle");


    public YandexMusicMainPageComponents openPage() {
        open("https://music.yandex.ru/");
         return this;
    }

    public YandexMusicMainPageComponents checkBlockHeader(String blockHeaderName){
        $(".centerblock").shouldHave(text(blockHeaderName));
        return this;
    }

    public YandexMusicMainPageComponents checkSubtitle(String title, String subtitle){
        blockSubtitle.findBy(text(title)).parent().shouldHave(text(subtitle));
        return this;
    }

    public YandexMusicMainPageComponents opecCategory(String title){
        blockSubtitle.findBy(text(title)).click();
        return this;
    }

    @EnumSource(Language.class)
    public YandexMusicMainPageComponents selectLanguage(Language language){
        languageSwitcher.scrollIntoView(true).click();
        languageSwitcherSelector.findBy(text(language.toString())).click();
        return this;
    }

    @EnumSource(Language.class)
    public YandexMusicMainPageComponents checkHeader(Language language) {
        pageHeader.shouldHave(text(language.header));
        return this;
    }


}
