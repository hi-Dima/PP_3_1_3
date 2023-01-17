package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.dao.UserRepository;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl  implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;




    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

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
        return mapRolesToAuthorities(userWithRole.getRoles());

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r-> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
        }
    @Override
    public void addUser(User user){
        Collection<Role> roles= new ArrayList<>();
        Role roleUser = new Role("ROLE_USER");
        roleRepository.saveRole(roleUser);
        roles.add(roleUser);
        user.setRoles(roles);
        userRepository.save(user);
    }



    @Override
    public void updateUser(User user) {
        userRepository.save(user);
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
    public List<Role> getUserRoles() {
        return roleRepository.getAllRoles();
    }

}

