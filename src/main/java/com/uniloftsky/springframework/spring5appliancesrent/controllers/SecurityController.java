package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import com.uniloftsky.springframework.spring5appliancesrent.model.User;
import com.uniloftsky.springframework.spring5appliancesrent.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class SecurityController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("passwordError", false);
        model.addAttribute("repeatPasswordError", false);
        return "signup_form";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, @RequestParam("repeat_password") String password) {
        if ((result.hasErrors() && user.getPassword().isBlank()) || result.hasErrors() || user.getPassword().isBlank() || !password.equals(user.getPassword())) {
            model.addAttribute("passwordError", true);
            model.addAttribute("repeatPasswordError", true);
            return "signup_form";
        }
        userService.save(user);
        model.addAttribute("registered", true);
        return "signin_form";
    }

}
