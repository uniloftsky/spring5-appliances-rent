package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.item.ItemAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.renting.RentingAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.user.UserAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.model.*;
import com.uniloftsky.springframework.spring5appliancesrent.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.Set;

@Controller
public class AdminController {

    private final ItemService itemService;
    private final RentingService rentingService;
    private final UserService userService;
    private final TypeService typeService;
    private final CategoryService categoryService;
    private final Comparator<Item> itemComparatorAscById = new ItemAscComparatorById();
    private final Comparator<Renting> rentingComparatorAscById = new RentingAscComparatorById();
    private final Comparator<User> userComparatorAscById = new UserAscComparatorById();

    public AdminController(ItemService itemService, RentingService rentingService, UserService userService, TypeService typeService, CategoryService categoryService) {
        this.itemService = itemService;
        this.rentingService = rentingService;
        this.userService = userService;
        this.typeService = typeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin")
    public String getAdminPanel(Model model) {
        model.addAttribute("type", new Type());
        model.addAttribute("category", new Category());
        return "admin/admin";
    }

    @GetMapping(value = "/itemDetail", params = "id")
    public String getItemDetail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("type", new Type());
        model.addAttribute("category", new Category());
        return "admin/itemDetail";
    }

    @GetMapping(value = "/userDetail", params = "id")
    public String getUserDetail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("userItems", userService.getUserItems(userService.findById(id), itemComparatorAscById));
        model.addAttribute("type", new Type());
        model.addAttribute("category", new Category());
        return "admin/userDetail";
    }

    @GetMapping(value = "/itemDelete", params = "id")
    public String deleteItem(@RequestParam("id") Long id) {
        itemService.delete(itemService.findById(id));
        return "redirect:/admin";
    }

    @GetMapping(value = "/userDelete", params = "id")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(userService.findById(id));
        return "redirect:/admin";
    }

    @PostMapping("/typeAdd")
    public String typeAdd(@ModelAttribute("type") Type type) {
        typeService.save(type);
        return "redirect:/admin";
    }

    @PostMapping("/categoryAdd")
    public String categoryAdd(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/admin";
    }


    @ModelAttribute("posts")
    public Set<Item> getPosts() {
        return itemService.findAllSortedById(itemComparatorAscById);
    }

    @ModelAttribute("rentings")
    public Set<Renting> getRentings() {
        return rentingService.findAllSortedById(rentingComparatorAscById);
    }

    @ModelAttribute("users")
    public Set<User> getUsers() {
        return userService.findAllSortedById(userComparatorAscById);
    }

    @ModelAttribute("activePosts")
    public Set<Item> getActivePosts() {
        return itemService.findAllActive();
    }

}
