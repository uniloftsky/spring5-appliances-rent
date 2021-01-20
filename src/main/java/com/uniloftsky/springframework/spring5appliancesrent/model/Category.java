package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Category extends BaseEntity {

    public Category() {
    }

    public Category(Type type, String categoryName) {
        this.type = type;
        this.categoryName = categoryName;
    }

    @ManyToOne
    private Type type;

    private String categoryName;

}
