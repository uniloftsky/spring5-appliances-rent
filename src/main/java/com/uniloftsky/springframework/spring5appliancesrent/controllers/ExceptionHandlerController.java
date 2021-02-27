package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("/forbidden")
    public String forbiddenHandler() {
        return "forbiddenPage";
    }

}
