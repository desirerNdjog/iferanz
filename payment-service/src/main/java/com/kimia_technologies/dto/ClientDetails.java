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
 * @since 06/09/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientDetails implements Serializable {
    private String originatedBy;
}
