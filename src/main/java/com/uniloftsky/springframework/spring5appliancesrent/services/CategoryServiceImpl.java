package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Category;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> findAll() {
        return new HashSet<>(categoryRepository.findAll());
    }

    @Override
    public Set<Category> findAllSortedById(Comparator<Category> comparator) {
        TreeSet<Category> sortedCategories = new TreeSet<>(comparator);
        categoryRepository.findAll().stream().iterator().forEachRemaining(sortedCategories::add);
        return sortedCategories;
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isEmpty()) {
            throw new RuntimeException("Expected category not found!");
        }
        return optional.get();
    }

    @Override
    public Category save(Category obj) {
        return categoryRepository.save(obj);
    }

    @Override
    public List<Category> saveAll(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public void delete(Category obj) {
        categoryRepository.delete(obj);
    }
}
