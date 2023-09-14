package com.kimia_technologies.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthorityResponse implements Serializable {
    private Integer idAuthorities;
    private String name;
    private String description;
}
