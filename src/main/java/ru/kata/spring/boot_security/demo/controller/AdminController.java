package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addUser")
    public String getInfoForCreateUsers(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", userService.getUserRoles());
        return "addUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        user.setRoles(userService.getRolesByRoleName(user.getRoles().toString()));
        userService.addUser(user);

        return "redirect:/admin";
    }


    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user ) {
        user.setRoles(userService.getRolesByRoleName(user.getRoles().toString()));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("delete") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}


