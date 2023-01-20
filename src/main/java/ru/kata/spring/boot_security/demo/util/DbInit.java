package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
@Component
public class DbInit {
    private final UserService userService;
    @Autowired
    public DbInit(UserService userService) {
        this.userService = userService;

    }

    @PostConstruct
    private void dataBaseInit() {

        Set<Role> adminRole= new HashSet<>();
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role ("ROLE_ADMIN");
        adminRole.add(roleUser);
        adminRole.add(roleAdmin);
        userService.saveRole(roleAdmin);
        userService.saveRole(roleUser);
        Set<Role> userRole= new HashSet<>();
        userRole.add(roleUser);


        User newUser = new User("User","pass","@user");
        User newAdmin = new User("Admin","pass","@admin");

        newUser.setRoles(userRole);
        newAdmin.setRoles( adminRole);
        userService.addUser(newAdmin);
        userService.addUser(newUser);

    }
}

