package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item extends BaseEntity {

    @NotNull(message = "{offer.field.NotNull}")
    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @NotBlank(message = "{offer.field.NotNull}")
    private String name;

    @ElementCollection
    private List<String> img = new ArrayList<>();

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
