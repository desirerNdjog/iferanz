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
 * @since 12/09/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordReset implements Serializable {
    private String password;
}
