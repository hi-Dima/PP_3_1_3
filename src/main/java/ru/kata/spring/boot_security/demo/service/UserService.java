package ru.kata.spring.boot_security.demo.service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface UserService {
//    void saveUser(User user);
    List<User> getAllUsers();
    User userInfo(int id);
    Collection<? extends GrantedAuthority> getUserAuthorities(String user);


//    void updateUser(User user);
//
//    void deleteUser(int id);
}
