package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.item.ItemAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.renting.RentingAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.user.UserAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.services.ItemService;
import com.uniloftsky.springframework.spring5appliancesrent.services.RentingService;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Comparator;
import java.util.Set;

@Controller
public class AdminController {

    private final ItemService itemService;
    private final RentingService rentingService;
    private final UserService userService;
    private final Comparator<Item> itemComparatorAscById = new ItemAscComparatorById();
    private final Comparator<Renting> rentingComparatorAscById = new RentingAscComparatorById();
    private final Comparator<User> userComparatorAscById = new UserAscComparatorById();

    public AdminController(ItemService itemService, RentingService rentingService, UserService userService) {
        this.itemService = itemService;
        this.rentingService = rentingService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAdminPanel() {
        return "admin";
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