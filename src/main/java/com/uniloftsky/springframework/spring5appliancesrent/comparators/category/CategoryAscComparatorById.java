package com.uniloftsky.springframework.spring5appliancesrent.comparators.category;

import com.uniloftsky.springframework.spring5appliancesrent.model.Category;

import java.util.Comparator;

public class CategoryAscComparatorById implements Comparator<Category> {

    @Override
    public int compare(Category o1, Category o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
