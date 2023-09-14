package com.kimia_technologies.dto;

import com.kimia_technologies.models.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdministorResponse {
    private Integer idAdministrator;
    private String name;
    private String phone;
    private String image;
    private String sexe;
    private User user;
}
