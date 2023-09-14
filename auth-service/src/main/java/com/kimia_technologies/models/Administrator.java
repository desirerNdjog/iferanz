package com.kimia_technologies.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(schema = "public", name = "administrator")
public class Administrator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdministrator;
    @Column(nullable = false, updatable = true)
    private String name;
    @Column(nullable = false, updatable = true)
    private String phone;
    @Column(nullable = true, updatable = true)
    private String image;
    @Column(nullable = false, updatable = false)
    private String sexe;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "idUser", nullable = false, updatable = false)
    private User user;
}
