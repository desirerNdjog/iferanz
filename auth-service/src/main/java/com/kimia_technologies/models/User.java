package com.kimia_technologies.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(schema = "public", name = "user")
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    @Column(nullable = false, updatable = true)
    private String email;
    @Column(nullable = false, updatable = true)
    private String password;
    @Column(nullable = true, updatable = true)
    private String passwordResetToken;
    @Column(nullable = true, updatable = true)
    private String passwordResetOtp;
    @Column(nullable = false, updatable = true)
    private String phone;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "idProfil", updatable = true, nullable = false)
    private Profil profil;
    @Column(name = "active", nullable = false, updatable = true)
    private Boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
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
