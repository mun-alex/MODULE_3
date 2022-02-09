package kz.bitlab.m3_ch1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/cities")
public class CitiesController {

    @GetMapping
    public String getCities() {
       return "/cities";
    }
}
