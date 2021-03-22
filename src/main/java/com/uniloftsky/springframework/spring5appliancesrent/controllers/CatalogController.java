package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class CatalogController {

//    @GetMapping("/catalog")
//    public String getCatalogPage() {
//        return "redirect:/catalogFilter?type.typeName=&category.categoryName=";
//    }
//
//    @GetMapping("catalogFilter")
//    public String filterItems(ItemPage itemPage, ItemSearchCriteria itemSearchCriteria, Model model) {
//        Page<Item> pages = itemService.getCatalogItems(itemPage, itemSearchCriteria);
//        Integer maxPage = pages.getTotalPages();
//        model.addAttribute("maxPage", maxPage);
//        model.addAttribute("catalogItems", pages);
//        List<Integer> pageNumbers = IntStream.rangeClosed(1, pages.getTotalPages()).boxed().collect(Collectors.toList());
//        model.addAttribute("pageNumbers", pageNumbers);
//        model.addAttribute("currentPage", pages.getNumber());
//        return "catalog";
//    }
//
//    @GetMapping("/searchBox")
//    public String searchBox(@RequestParam("searchField") String field, Model model) {
//        model.addAttribute("catalogItems", itemService.searchBox(field, field, field, field));
//        model.addAttribute("maxPage", 0);
//        model.addAttribute("pageNumbers", 1);
//        model.addAttribute("currentPage", 0);
//        return "catalog";
//    }

}
