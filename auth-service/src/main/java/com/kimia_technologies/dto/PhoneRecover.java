package com.kimia_technologies.dto;

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
public class PhoneRecover implements Serializable {
    private String phone;
}
