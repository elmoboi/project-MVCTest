package ru.olejik.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
@Controller
public class MenuController {
    @GetMapping()
    public String index() {
        return "/index";
    }
}
