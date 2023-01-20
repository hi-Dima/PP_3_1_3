package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
@Repository
public class UserRepositoryImpl {
    private  final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public  void  addUser(User user){

        entityManager.persist(user);

    }

    public  void  updateUser(User user){
        entityManager.merge(user);
    }
}
