package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class FormLoginController {
    private final UserService userService;
    private  final RoleService roleService;

    public FormLoginController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal, Model model) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "userInfo";
    }
    @GetMapping("/admin")
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("roles", roleService.getUserRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user",  userService.findUserByUsername(principal.getName()));
        return "admin";
    }


}
