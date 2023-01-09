package ru.kata.spring.boot_security.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "userT2")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;
    @Column
    private String role;

    @Column
    private String pass;

    @Column
    private String email;

    public String getRole() {
        return role;
    }
}
