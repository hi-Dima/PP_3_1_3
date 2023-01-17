package ru.kata.spring.boot_security.demo.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
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

        Collection<Role> adminRole= new ArrayList<>();
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role ("ROLE_ADMIN");
        userService.saveRole(roleAdmin);
        userService.saveRole(roleUser);
        adminRole.add(roleUser);
        adminRole.add(roleAdmin);

        Collection<Role> userRole= new ArrayList<>();
        userRole.add(roleUser);


        User newUser = new User("юзер","pass","@ivan");
        User newAdmin = new User("Админ","pass","@ivan");

        newUser.setRoles(userRole);
        newAdmin.setRoles( adminRole);
        userService.saveUser(newUser);
        userService.saveUser(newAdmin);

    }
}

