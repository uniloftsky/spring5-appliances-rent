package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.services.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CatalogController {

    private final ItemService itemService;

    public CatalogController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/catalog")
    public String getPageableItems(@PageableDefault(sort = {"id"}, size = 2) Pageable pageable, Model model) {
        Page<Item> pages = itemService.findAll(pageable);
        Integer maxPage = pages.getTotalPages();
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("page", pages);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, pages.getTotalPages()).boxed().collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("currentPage", pages.getNumber());
        return "catalog";
    }

}
