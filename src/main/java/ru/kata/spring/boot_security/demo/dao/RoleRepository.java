package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.Set;

public interface RoleRepository {
    void  saveRole (Role role);
    Set<Role> getAllRoles();
    Set<Role> getRolesByRoleName(String role);
    Set<Role> getRolesByUserId(Integer id);
}
