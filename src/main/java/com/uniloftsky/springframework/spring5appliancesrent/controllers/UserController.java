package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/profile", params = "login")
    public String showProfile(Model model, @RequestParam("login") String login) {
        if(login.equals("anonymousUser")) {
            return "signin_form";
        }
        model.addAttribute("user", userService.findByLogin(login));
        model.addAttribute("rentingHistory", userService.findByLogin(login).getRentings());
        model.addAttribute("currentItems", userService.findByLogin(login).getItems().stream().filter(Item::isActive));
        return "profile";
    }

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication) {
        return "redirect:/profile?login=" + authentication.getName();
    }

    @GetMapping("/editProfile")
    public String editProfileInit(Model model, Authentication authentication) {
        model.addAttribute("user", userService.findByLogin(authentication.getName()));
        return "profileEdit";
    }

    @PostMapping("/editProfile")
    public String editProfileProcess(@ModelAttribute User user) {
        User savedUser = userService.save(user);
        return "redirect:/profile?login=" + savedUser.getLogin();
    }

    @PostMapping("/changePassword")
    public String editPasswordForm(@RequestParam("newPassword") String newPassword, Authentication authentication) {
        User user = userService.changePassword(authentication, newPassword);
        return "redirect:/profile";
    }

}
