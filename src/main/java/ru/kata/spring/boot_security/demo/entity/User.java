package ru.kata.spring.boot_security.demo.entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
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
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
    @Override
    public String toString() {
        return
                "id=" + id +
                        ", firstName='" + firstName + '\'' +
                        ", surname='" + surname + '\'' +
                        ", username='" + username + '\'' +
                        ", roles=" + roles +
                        ", pass='" + pass + '\'' +
                        ", email='" + email + '\'' +
                        ", age=" + age +
                        '}';
    }

}
