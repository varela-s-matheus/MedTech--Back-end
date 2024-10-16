package com.br.medtech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Table(name = "usuarios_acesso")
public class UserAcess implements UserDetails {
    //Classe para armazenamento de senhas e informações para Login
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;
    private char userType;
    private int registerId;
    private String email;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAcess)) return false;
        UserAcess userAcess = (UserAcess) o;
        return email.equals(userAcess.email); // Use fields that do not refer back to the same object
    }

    @Override
    public int hashCode() {
        return Objects.hash(email); // Ensure this doesn't call any recursive fields
    }

    public UserAcess(int registerId, String email, String password, char userType) {
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.registerId = registerId;
    }
}
