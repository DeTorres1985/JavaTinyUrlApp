package ru.cmstricks.tinyUrlWebApp.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "paid_links")
public class PaidLink {
    @Id
    @Column(name = "link", nullable = false, unique = true, columnDefinition = "VARCHAR(5)")
    private String link;

    @Column(name = "price", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price;

    public PaidLink() {
    }

    public PaidLink(String link, BigDecimal price) {
        this.link = link;
        this.price = price;
    }

    public String getlink() {
        return link;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
