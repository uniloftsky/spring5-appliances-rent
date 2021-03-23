package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.item.ItemDescComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemPage;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemSearchCriteria;
import com.uniloftsky.springframework.spring5appliancesrent.services.ItemService;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    private final UserService userService;
    private final ItemService itemService;

    private final Comparator<Item> comparator = new ItemDescComparatorById();

    public HomeController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping({"", "/", "index", "/index", "*", "/*", "*.html"})
    public String getIndexPage(Model model) {
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("lastPost", itemService.findAllSortedById(comparator).first());
        model.addAttribute("popularUsers", userService.getPopularUsers());
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

    @GetMapping("/searchBox")
    public String searchBox(@RequestParam("searchField") String field, Model model) {
        model.addAttribute("catalogItems", itemService.searchBox(field, field, field, field));
        model.addAttribute("maxPage", 0);
        model.addAttribute("pageNumbers", 1);
        model.addAttribute("currentPage", 0);
        return "catalog";
    }

    @GetMapping("/forbidden")
    public String forbiddenHandler() {
        return "forbiddenPage";
    }

}
