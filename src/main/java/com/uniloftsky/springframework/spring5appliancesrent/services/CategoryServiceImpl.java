package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Category;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
