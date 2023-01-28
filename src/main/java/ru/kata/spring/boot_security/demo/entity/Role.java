package ru.kata.spring.boot_security.demo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role  implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(unique = true)
    private String role;

    public String getRole() {
        return role;
    }

    public Role(String role) {
        this.role = role;
    }
    public Role() {
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return  this.role;
    }
}
