package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Renting extends BaseEntity {

    public Renting() {
    }

    public Renting(User user, Item item, BigDecimal price, LocalDate date) {
        this.user = user;
        this.item = item;
        this.price = price;
        this.date = date;
    }

    @ManyToOne
    private User user;

    @OneToOne
    private Item item;

    private BigDecimal price;
    private LocalDate date;

}
