package ru.cmstricks.tinyUrlWebApp.repositories.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tinyurls")
public class TinyUrl {
    private static final Long INITIAL_VIEW_COUNT = 0L;

    @Id
    @Column(name = "link", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String link;

    @Column(name = "url", nullable = false, columnDefinition = "VARCHAR(255)")
    private String url;

    @Column(name = "opened", nullable = false)
    private Long opened;

    public void setOpened(Long opened) {
        this.opened = opened;
    }

    public Long getOpened() {
        return opened;
    }

    public TinyUrl(String link, String url) {
        this(link, url, INITIAL_VIEW_COUNT);
    }

    public TinyUrl() {

    }

    public TinyUrl(String link, String url, Long opened) {
        this.link = link;
        this.url = url;
        this.opened = opened;
    }

    public String getLink() {
        return link;
    }

    public String getUrl() {
        return url;
    }
}
