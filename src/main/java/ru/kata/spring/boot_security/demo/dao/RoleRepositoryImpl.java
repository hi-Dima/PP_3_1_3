package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public  class RoleRepositoryImpl implements RoleRepository {
    private  final EntityManager entityManager;

    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public  void  saveRole(Role role){
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }
}
