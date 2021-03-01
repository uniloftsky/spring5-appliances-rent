package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Category;

import java.util.Comparator;
import java.util.Set;

public interface CategoryService extends GenericService<Category, Long> {

    Set<Category> findAllSortedById(Comparator<Category> comparator);

}
