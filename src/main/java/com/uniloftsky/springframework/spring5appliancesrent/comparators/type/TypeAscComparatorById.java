package com.uniloftsky.springframework.spring5appliancesrent.comparators.type;

import com.uniloftsky.springframework.spring5appliancesrent.model.Type;

import java.util.Comparator;

public class TypeAscComparatorById implements Comparator<Type> {

    @Override
    public int compare(Type o1, Type o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
