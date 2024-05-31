package ru.cmstricks.tinyUrlWebApp.accessingdatamysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "precooked_free_prelinks")
public class FreePreLink {
    @Id
    @Column(name = "innerlink", nullable = false, unique = true, columnDefinition = "VARCHAR(5)")
    private String innerlink;
}
