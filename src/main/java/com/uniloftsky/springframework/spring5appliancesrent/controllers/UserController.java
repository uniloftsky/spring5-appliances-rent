package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.comparators.item.ItemAscComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.item.ItemDescComparatorById;
import com.uniloftsky.springframework.spring5appliancesrent.comparators.renting.RentingDescComparatorByDate;
import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.Renting;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Comparator;

@Controller
public class UserController {

    private final UserService userService;
    private final Comparator<Item> itemAscComparatorById = new ItemAscComparatorById();
    private final Comparator<Item> itemDescComparatorById = new ItemDescComparatorById();
    private final Comparator<Renting> rentingDescComparatorByDate = new RentingDescComparatorByDate();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/profile", params = "login")
    public String showProfile(Model model, @RequestParam("login") String login) {
        model.addAttribute("user", userService.findByLogin(login));
        model.addAttribute("rentingHistory", userService.getUserRentings(userService.findByLogin(login), rentingDescComparatorByDate));
        model.addAttribute("currentItems", userService.getUserItems(userService.findByLogin(login), itemDescComparatorById));
        return "profile";
    }

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication) {
        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "signin_form";
        } else {
            return "redirect:/profile?login=" + authentication.getName();
        }
    }

    @GetMapping("/editProfile")
    public String editProfileInit(Model model, Authentication authentication) {
        model.addAttribute("user", userService.findByLogin(authentication.getName()));
        model.addAttribute("passwordError", false);
        return "profileEdit";
    }

    @PostMapping("/editProfile")
    public String editProfileProcess(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "profileEdit";
        }
        userService.save(user);
        return "redirect:/profile";
    }

    @PostMapping("/changePassword")
    public String editPasswordForm(RedirectAttributes rA, @RequestParam("newPassword") String newPassword, Authentication authentication, Model model) {
        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "signin_form";
        }
        if (newPassword.isBlank()) {
            model.addAttribute("passwordError", true);
            model.addAttribute("user", userService.findByLogin(authentication.getName()));
            return "profileEdit";
        }
        userService.changePassword(authentication, newPassword);
        return "redirect:/profile";
    }

}
