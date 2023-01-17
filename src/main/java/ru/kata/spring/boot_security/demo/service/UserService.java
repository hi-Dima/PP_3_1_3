package ru.kata.spring.boot_security.demo.service;
import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import java.util.Collection;
import java.util.List;

public interface UserService {
//    void saveUser(User user);
    List<User> getAllUsers();
    User userInfo(int id);
    User findUserByUsername (String username);
    Collection<? extends GrantedAuthority> getUserAuthorities(String user);
    void saveUser(User user);
    void addUser (User user);

    void updateUser(User user);
    void saveRole (Role role);
    void deleteUser(int id);
    List<Role> getUserRoles();

}
