package com.kimia_technologies.dto;

import com.kimia_technologies.models.Profil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {
    private Integer idUser;
    private String email;
    private Profil profil;
    private String phone;
    private String passwordResetToken;
    private String passwordResetOtp;
    private Boolean isActive;
}
