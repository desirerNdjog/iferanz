package com.kimia_technologies.mapper;

import com.kimia_technologies.dto.ProfilResponse;
import com.kimia_technologies.models.Profil;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 04/09/2023
 */

@Service
public class ProfilResponseMapper implements Function<Profil, ProfilResponse> {
    @Override
    public ProfilResponse apply(Profil profil) {
        return new ProfilResponse(
                profil.getIdProfil(),
                profil.getName(),
                profil.getAuthorities().stream().collect(Collectors.toList())
        );
    }
}
