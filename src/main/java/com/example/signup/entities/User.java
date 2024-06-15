package com.example.signup.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;


@Data
@Entity
@Table(name = "testusers")
public class User implements UserDetails {
    @Serial
    private static final long serialVersionUID = -3546862197939974800L;

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@Column(name = "id")
private long id;
@Column(name = "name")
private String username;
@Column(name = "email")
private String email;
@Column(name = "password")
private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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
