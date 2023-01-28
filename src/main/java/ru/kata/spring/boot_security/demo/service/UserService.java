package ru.kata.spring.boot_security.demo.service;
import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserService {
//    void saveUser(User user);
    List<User> getAllUsers();
    User userInfo(int id);
    User findUserByUsername (String login);
//    Collection<? extends GrantedAuthority> getUserAuthorities(String user);
    void updateUser(User user);
    void saveRole (Role role);
    void deleteUser(int id);
    Set<Role> getUserRoles();
    void  addUser(User user);
    Set<Role> getRolesByRoleName(String role);
    Set<Role> getRolesByUserId(Integer Id);
}
