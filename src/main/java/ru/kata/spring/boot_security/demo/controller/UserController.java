package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/new")
    public String getViewForCreateUsers(@ModelAttribute("user") User user) {

        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/id")
    public String userInfo(@RequestParam("id") int id, Model model, Principal principal) {
        model.addAttribute("user", userService.userInfo(id));
        User user = userService.findUserByUsername(principal.getName());
        if (user.getRoles().toString().contains("ROLE_ADMIN")) {
            return "adminInfo";
        }
        return "userInfo";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit")
    public String getViewForEditUser(Model model, @RequestParam("edit") int id) {
        model.addAttribute("user", userService.userInfo(id));

        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user ) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("delete") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}


