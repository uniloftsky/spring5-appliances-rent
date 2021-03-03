package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.CategoryAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.TypeAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.model.Category;
import com.uniloftsky.springframework.spring5appliancesrent.model.Type;
import com.uniloftsky.springframework.spring5appliancesrent.services.CategoryService;
import com.uniloftsky.springframework.spring5appliancesrent.services.TypeService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Comparator;
import java.util.Set;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final TypeService typeService;
    private final CategoryService categoryService;

    public GlobalControllerAdvice(TypeService typeService, CategoryService categoryService) {
        this.typeService = typeService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("typeList")
    public Set<Type> getTypeList() {
        Comparator<Type> comparator = new TypeAscComparatorById();
        return typeService.findAllSortedById(comparator);
    }

    @ModelAttribute("categoryList")
    public Set<Category> getCategoryList() {
        Comparator<Category> comparator = new CategoryAscComparatorById();
        return categoryService.findAllSortedById(comparator);
    }

}
