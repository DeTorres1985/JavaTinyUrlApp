package ru.cmstricks.tinyUrlWebApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "precooked_paid_prelinks")
public class PaidPreLink {
    @Id
    @Column(name = "innerlink", nullable = false, unique = true, columnDefinition = "VARCHAR(5)")
    private String innerlink;

    @Column(name = "price", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price;
}
