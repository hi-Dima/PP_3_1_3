package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();
    User userInfo(int id);
    User findUserByUsername (String login);

    void updateUser(User user);
    void deleteUser(int id);
    void  addUser(User user);

}
