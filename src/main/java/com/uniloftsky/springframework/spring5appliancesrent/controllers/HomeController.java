package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.item.ItemDescComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.services.CategoryService;
import com.uniloftsky.springframework.spring5appliancesrent.services.ItemService;
import com.uniloftsky.springframework.spring5appliancesrent.services.TypeService;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Comparator;
import java.util.Set;

@Controller
public class HomeController {

    private final UserService userService;
    private final ItemService itemService;
    private final CategoryService categoryService;
    private final TypeService typeService;

    private final Comparator<Item> comparator = new ItemDescComparatorById();

    public HomeController(UserService userService, ItemService itemService, CategoryService categoryService, TypeService typeService) {
        this.userService = userService;
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.typeService = typeService;
    }

    @GetMapping({"", "/", "index", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("lastPost", getLastPost());
        model.addAttribute("popularUsers", userService.getPopularUsers());
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

    private Item getLastPost() {
        return itemService.findAllSortedById(comparator).last();
    }

    @ModelAttribute("lastPosts")
    public Set<Item> getLastPosts() {
        return itemService.getLastPostsIndexPage();
    }

/*    @ModelAttribute("popularUsers")
    public Set<User> getPopularUsers() {
        for(User u : userService.getPopularUsers()) {
            System.out.println("loop");
            System.out.println(u.getId());
        }
        return userService.getPopularUsers();
    }*/

}
