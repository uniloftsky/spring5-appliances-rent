package com.uniloftsky.springframework.spring5appliancesrent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Type extends BaseEntity {

    public Type() {}

    public Type(String typeName) {
        this.typeName = typeName;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "type")
    private Set<Category> categories = new HashSet<>();

    private String typeName;

}
