package com.kimia_technologies.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "public", name = "profil")
public class Profil implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfil;
    @Column(nullable = false, updatable = true)
    private String name;
    @JoinTable(name = "profil_authorities", joinColumns = {@JoinColumn(name = "idProfil")}, inverseJoinColumns = {@JoinColumn(name = "idAuthorities")})
    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Authority> authorities;
}
