package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class FormLoginController {
    private final UserService userService;

    public FormLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String UserInfo(Principal principal, Model model) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "userInfo";
    }
    @GetMapping("/admin")
    public String AdminInfo( Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }


}
