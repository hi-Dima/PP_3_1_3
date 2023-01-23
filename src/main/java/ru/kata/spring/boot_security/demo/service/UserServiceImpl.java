package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserRepositoryImpl;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.dao.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl  implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserRepositoryImpl userRepositoryImpl;



    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRepositoryImpl userRepositoryImpl) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.userRepositoryImpl = userRepositoryImpl;
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
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }


    @Override
    public Collection<? extends GrantedAuthority> getUserAuthorities( String user) {
        User userWithRole =  userRepository.findByUsername(user).get();
        return CollectRolesToAuthorities(userWithRole.getRoles());

    }
    private Collection<? extends GrantedAuthority> CollectRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r-> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }
    @Override
    public void addUser(User user){
            userRepositoryImpl.addUser(user);

    }

    @Override
    public Set<Role> getRolesByRoleName(String role) {
        return roleRepository.getRolesByRoleName(role);
    }

    @Override
    public Set<Role> getRolesByUserId(Integer Id) {
        return roleRepository.getRolesByUserId(Id);
    }


    @Override
    public void updateUser(User user) {
        userRepositoryImpl.updateUser(user);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.saveRole(role);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Set<Role> getUserRoles() {
        return roleRepository.getAllRoles();
    }

}

