package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

// Класс сервиса. Внедряем зависимость от UserDao c помощью конструктора. Аннотации @Transactional
// ставим только в методах, где идет по изменению/удалению  значений из БД
@Service
public class UserServiceImpl  implements UserService{
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserRepository userRepository, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
//    @Override
//    public void saveUser(User user) {
//        userRepository.saveUser(user);
//    }

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
    public Collection<? extends GrantedAuthority> getUserAuthorities( String user) {
       User userWithRole =  (User)userDetailsService.loadUserByUsername(user);
        return Collections.singletonList(new SimpleGrantedAuthority(userWithRole.getRole()));

    }

//    @Override
//    public User userInfo(int id) {
//        return userDao.userInfo(id);
//    }
//    @Transactional
//    @Override
//    public void updateUser(User user) {
//        userDao.updateUser(user);
//    }
//    @Transactional
//    @Override
//    public void deleteUser(int id) {
//        userDao.deleteUser(id);
//    }

}
