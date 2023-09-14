package com.kimia_technologies.mapper;

import com.kimia_technologies.dto.AdministorResponse;
import com.kimia_technologies.models.Administrator;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 05/09/2023
 */

@Service
public class AdministratorServiceMapper implements Function<Administrator, AdministorResponse> {
    @Override
    public AdministorResponse apply(Administrator administrator) {
        return new AdministorResponse(
                administrator.getIdAdministrator(),
                administrator.getName(),
                administrator.getPhone(),
                administrator.getSexe(),
                administrator.getImage(),
                administrator.getUser()
        );
    }
}
