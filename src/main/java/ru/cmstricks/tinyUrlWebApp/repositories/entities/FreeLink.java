package ru.cmstricks.tinyUrlWebApp.repositories.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "free_links")
public class FreeLink {
    @Id
    @Column(name = "link", nullable = false, unique = true, columnDefinition = "VARCHAR(5)")
    private String link;

    public FreeLink(String link) {
        this.link = link;
    }

    public FreeLink() {
    }

    public String getLink() {
        return link;
    }
}
