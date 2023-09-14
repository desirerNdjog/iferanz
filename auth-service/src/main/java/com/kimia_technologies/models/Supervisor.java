package com.kimia_technologies.models;

import com.kimia_technologies.enums.Sexe;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(schema = "public", name = "supervisor")
public class Supervisor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSupervisor;
    @Column(nullable = false, updatable = true)
    private String name;
    @Column(nullable = false, updatable = true)
    private String phone;
    @Column(nullable = true, updatable = true)
    private String image;
    @Column(nullable = false, updatable = true)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "idUser", updatable = false, nullable = false)
    private User user;
}
