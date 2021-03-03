package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemPage;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemSearchCriteria;
import com.uniloftsky.springframework.spring5appliancesrent.services.CategoryService;
import com.uniloftsky.springframework.spring5appliancesrent.services.ItemService;
import com.uniloftsky.springframework.spring5appliancesrent.services.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CatalogController {

    private final ItemService itemService;
    private final TypeService typeService;
    private final CategoryService categoryService;

    public CatalogController(ItemService itemService, TypeService typeService, CategoryService categoryService) {
        this.itemService = itemService;
        this.typeService = typeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/catalog")
    public String getCatalogPage(Model model) {
        return "redirect:/catalogFilter?type.typeName=&category.categoryName=";
    }

    @GetMapping("catalogFilter")
    public String filterItems(ItemPage itemPage, ItemSearchCriteria itemSearchCriteria, Model model) {
        Page<Item> pages = itemService.getCatalogItems(itemPage, itemSearchCriteria);
        Integer maxPage = pages.getTotalPages();
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("catalogItems", pages);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, pages.getTotalPages()).boxed().collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("currentPage", pages.getNumber());
        return "catalog";
    }

}
