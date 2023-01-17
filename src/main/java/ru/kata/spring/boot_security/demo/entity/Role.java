package ru.kata.spring.boot_security.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(unique = true)
    private String role;

    public String getRole() {
        return role;
    }

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(String role) {
        this.role = role;
    }
    public Role() {
    }
}
