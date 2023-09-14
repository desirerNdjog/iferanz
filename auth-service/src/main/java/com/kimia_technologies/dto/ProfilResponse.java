package com.kimia_technologies.dto;

import com.kimia_technologies.models.Authority;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfilResponse implements Serializable {
    private Integer idProfil;
    private String name;
    private List<Authority> authorities;
}
