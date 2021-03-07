package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.services.ItemService;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class OffersController {

    private final ItemService itemService;
    private final UserService userService;

    public OffersController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping("/postOffer")
    public String initPostOfferForm(Model model) {
        model.addAttribute("offer", new Item());
        return "post_form";
    }

    //todo
    @PostMapping("/postOffer")
    public String processPostOfferForm(@ModelAttribute Item item, Authentication authentication) {
        item.setImg("img"); //need to be changed
        Item savedItem = itemService.save(item, authentication);
        return "redirect:/offer?id=" + savedItem.getId();
    }

    @GetMapping(value = "/offer", params = "id")
    public String getOffer(@Param("id") Long id, Model model) {
        model.addAttribute("offer", itemService.findById(id));
        return "offer";
    }

    @GetMapping("/offer")
    public String getOfferPage() {
        return "redirect:/offer?id=1";
    }

    @ModelAttribute("similarPosts")
    public Set<Item> getSimilarPosts() {
        return itemService.getSimilarPosts();
    }

    @ModelAttribute("lastPosts")
    public Set<Item> getLastPosts() {
        return itemService.getLastPostsIndexPage();
    }

    //todo
    @GetMapping("/order")
    public String getOrder() {
        return "order";
    }

}