package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.services.CategoryService;
import com.uniloftsky.springframework.spring5appliancesrent.services.ItemService;
import com.uniloftsky.springframework.spring5appliancesrent.services.TypeService;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;

@Controller
public class HomeController {

    private final UserService userService;
    private final ItemService itemService;
    private final CategoryService categoryService;
    private final TypeService typeService;

    public HomeController(UserService userService, ItemService itemService, CategoryService categoryService, TypeService typeService) {
        this.userService = userService;
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.typeService = typeService;
    }

    @GetMapping({"", "/", "index", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "index";
    }

    @ModelAttribute("activePosts")
    public Set<Item> getAllActiveItems() {
        return itemService.findAllActive();
    }

    @ModelAttribute("allPosts")
    public Set<Item> getAllItems() {
        return itemService.findAll();
    }

    @ModelAttribute("lastPosts")
    public Set<Item> getLastPosts() {
        return itemService.getLastPosts();
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
//        model.addAttribute("users", userService.findAll());
        return "users";
    }
//
    @GetMapping("/postOffer")
    public String showPostForm(Model model) {
//        Item item = new Item();
//        model.addAttribute("username", principal.getName());
//        model.addAttribute("item", item);
//        model.addAttribute("types", typeService.findAll());
//        model.addAttribute("categories", categoryService.findAll());
        return "post_form";
    }
//
//    @PostMapping("/postOffer")
//    public String processPostForm(@ModelAttribute Item item, Principal principal) {
//        item.setDate(LocalDate.now());
//        item.setUser(userService.findByLogin(principal.getName()));
//        itemService.save(item);
//        return "redirect:/index";
//    }

}
