package ru.kata.spring.boot_security.demo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "userT2")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String surname;
    @Column
    private String username;
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "userT2_roles",joinColumns = @JoinColumn(name = "userT2_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new HashSet<>();

    @Column
    private String pass;

    @Column
    private String email;
    @Column
    private int age;


    public User(String firstName, String surname, int age, String username, String pass, String email, Set<Role> roles) {
        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.roles = roles;
        this.age = age;
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return pass;
    }

    public Set<String> getUserRoles() {
        Set<String> userRoles = new HashSet<>();
        for (Role i : this.getRoles()) {
            userRoles.add(i.getRole());
        }
        return userRoles;
    }

    public String getToStringRoles() {
        return roles.toString().replace("[", "")
                .replace("]", "");
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
