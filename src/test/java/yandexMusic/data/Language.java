package yandexMusic.data;

public enum Language {
    RU("Главное"),
    EN("Home"),
    UK("Головне");

    public final String header;

    Language(String header) {
        this.header = header;
    }
}
