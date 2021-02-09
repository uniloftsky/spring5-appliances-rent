package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Role extends BaseEntity {

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    private String name;

}
