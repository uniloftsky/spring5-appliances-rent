package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    Set<Category> findAll();
    Category findById(Long id);
    Category save(Category obj);
    List<Category> saveAll(List<Category> categories);
    void delete(Category obj);

}
