package ru.kata.spring.boot_security.demo.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "userT2")
public class User {
    public User(String username, String pass, String email) {
        this.username = username;
        this.pass = pass;
        this.email = email;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userT2_roles",joinColumns = @JoinColumn(name = "userT2_id"),
    inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Collection<Role> roles;

    @Column
    private String pass;

    @Column
    private String email;
}
