package ru.cmstricks.tinyUrlWebApp.repositories.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tinyurls")
public class TinyUrl {
    @Id
    @Column(name = "link", nullable = false, unique = true, columnDefinition = "VARCHAR(5)")
    private String link;

    @Column(name = "url", nullable = false, columnDefinition = "VARCHAR(255)")
    private String url;

    @Column(name = "opened", nullable = false)
    private Long opened;
}
