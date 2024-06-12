package ru.cmstricks.tinyUrlWebApp.dao;

public class TinyUrlDao {
    public TinyUrlDao(String link, String url) {
        this.link = link;
        this.url = url;
    }

    private String link;
    private String url;

    public String getLink() {
        return link;
    }

    public String getUrl() {
        return url;
    }
}
