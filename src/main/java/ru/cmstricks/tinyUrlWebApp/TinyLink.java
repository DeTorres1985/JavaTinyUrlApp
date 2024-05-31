package ru.cmstricks.tinyUrlWebApp.accessingdatamysql;

import jakarta.persistence.*;

@Entity
@Table(name = "tinylinks")
public class TinyLink {
    @Id
    @Column(name = "innerlink", nullable = false, unique = true, columnDefinition = "VARCHAR(5)")
    private String innerlink;

    @Column(name = "outerlink", nullable = false, columnDefinition = "VARCHAR(255)")
    private String outerlink;
}
