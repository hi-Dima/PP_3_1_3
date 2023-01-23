package ru.kata.spring.boot_security.demo.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "userT2")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinTable(name = "userT2_roles",joinColumns = @JoinColumn(name = "userT2_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column
    private String pass;

    @Column
    private String email;

    public User(String username, String pass, String email) {
        this.username = username;
        this.pass = pass;
        this.email = email;
    }

    public User() {
    }


}
