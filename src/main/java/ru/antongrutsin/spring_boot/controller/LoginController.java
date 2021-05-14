package ru.antongrutsin.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class LoginController {

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }

    @PostMapping("/authenticateTheUser")
    public String hello(){
        return "redirect:/products";
    }

}
