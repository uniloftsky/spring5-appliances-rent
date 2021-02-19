package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/users")
    public String listUsers(Model model) {
        return "users";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "profile";
    }

    @GetMapping("/editProfile")
    public String editProfileInit() {
        return "profileEdit";
    }

}
