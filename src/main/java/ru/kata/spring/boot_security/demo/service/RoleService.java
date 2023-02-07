package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.Set;

public interface RoleService {

    void saveRole (Role role);
    Set<Role> getUserRoles();
    Set<Role> getRolesByRoleName(String role);
    Set<Role> getRolesByUserId(Integer Id);
}
