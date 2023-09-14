package com.kimia_technologies.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 05/09/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {
    private String emailOrPhone;
    private String password;
}
