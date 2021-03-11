package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull(message = "{offer.field.NotNull}")
    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @NotBlank(message = "{offer.field.NotNull}")
    private String name;

    private String img;

    @NotBlank(message = "{user.field.NotBlank}")
    private String location;

    @NotNull(message = "{offer.field.NotNull}")
    private BigDecimal price;

    private LocalDate date;

    @NotBlank(message = "{user.field.NotBlank}")
    @Size(max = 1000, message = "{offer.field.Size}")
    private String description;

    private boolean active = true;
}
