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
@Table(schema = "public", name = "authorities")
public class Authority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAuthorities;
    @Column(nullable = false, updatable = false)
    private String name;
    @Column(nullable = true, updatable = true)
    private String description;
}
