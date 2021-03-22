package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Renting extends BaseEntity {

    @OneToOne
    private Item item;

    private BigDecimal price;
    private LocalDate date;

}
