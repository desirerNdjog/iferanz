package com.kimia_technologies.dto;

import com.kimia_technologies.enums.Sexe;
import com.kimia_technologies.models.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorResponse implements Serializable {
    private Integer idSupervisor;
    private String name;
    private String phone;
    private String image;
    private Sexe sexe;
    private User user;
}
