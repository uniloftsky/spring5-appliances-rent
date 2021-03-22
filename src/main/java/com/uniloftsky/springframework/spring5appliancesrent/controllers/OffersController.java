package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.services.ItemService;
import com.uniloftsky.springframework.spring5appliancesrent.services.RentingService;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class OffersController {

    private final ItemService itemService;
    private final UserService userService;
    private final RentingService rentingService;

    public OffersController(ItemService itemService, UserService userService, RentingService rentingService) {
        this.itemService = itemService;
        this.userService = userService;
        this.rentingService = rentingService;
    }

    @GetMapping("/postOffer")
    public String initPostOfferForm(Model model) {
        model.addAttribute("offer", new Item());
        model.addAttribute("fileError", false);
        return "post_form";
    }

    //todo image size
    @PostMapping("/postOffer")
    public String processPostOfferForm(@Valid @ModelAttribute("offer") Item item, BindingResult result, Authentication authentication, @RequestParam("itemImage") List<MultipartFile> file, Model model) throws IOException {
        if ((result.hasErrors() && file.isEmpty()) || file.isEmpty() || result.hasErrors() || file.size() > 3) {
            model.addAttribute("fileError", true);
            return "post_form";
        }
        Item savedItem = itemService.save(item, authentication, file);
        return "redirect:/offer?id=" + savedItem.getId();
    }

    @GetMapping(value = "/offer", params = "id")
    public String getOffer(@Param("id") Long id, Model model) {
        model.addAttribute("offer", itemService.findById(id));
        model.addAttribute("mainImg", itemService.getMainImage(itemService.findById(id)));
        model.addAttribute("otherImgs", itemService.getImages(itemService.findById(id)));
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
    @GetMapping(value = "/order", params = "id")
    public String getOrder(@RequestParam("id") Long id, Model model) {
        model.addAttribute("offer", itemService.findById(id));
        return "order";
    }

    @PostMapping("/makeOrder")
    public String processOrder(RedirectAttributes rA, @RequestParam("userOwner") String ownerLogin, @RequestParam("itemId") Long itemId, Model model) {
        User ownerUser = userService.findByLogin(ownerLogin);
        Item item = itemService.findById(itemId);
        rentingService.saveOrder(ownerUser, item);
        rA.addFlashAttribute("ordered", true);
        return "redirect:/offer?id=" + itemId;
    }

}