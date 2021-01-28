package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Item extends BaseEntity {

    public Item() {}

    public Item(Category category, User user, String name, String img, String location, BigDecimal price, LocalDate date, String description, boolean active) {
        this.category = category;
        this.user = user;
        this.name = name;
        this.img = img;
        this.location = location;
        this.price = price;
        this.date = date;
        this.description = description;
        this.active = active;
    }

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    private String name;
    private String img;
    private String location;
    private BigDecimal price;
    private LocalDate date;
    private String description;
    private boolean active = true;

}
