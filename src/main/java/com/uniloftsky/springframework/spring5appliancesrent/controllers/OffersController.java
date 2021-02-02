package com.uniloftsky.springframework.spring5appliancesrent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class OffersController {

    @GetMapping("/testD")
    public String test(@RequestParam("date") String date) {
        System.out.println(date);
        LocalDate date1 = LocalDate.parse(date);
        System.out.println(date1);
        return "index";
    }

}
