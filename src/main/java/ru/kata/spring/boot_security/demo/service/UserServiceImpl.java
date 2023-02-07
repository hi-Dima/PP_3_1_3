package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.dao.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;

    }

    @Override
    public User userInfo(int id) {
        return  userRepository.findById(id).get();
    }

    @Override
    public User findUserByUsername(String login) {
        return userRepository.findByUsername(login).get();
    }




    private Collection<? extends GrantedAuthority> CollectRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r-> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void addUser(User user){
        user.setPass(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }
    @Override
    @Transactional
    public void updateUser(User user) {
        if (!user.getPassword().equals(userRepository.findById(user.getId()).get().getPassword())) {
            user.setPass(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}

