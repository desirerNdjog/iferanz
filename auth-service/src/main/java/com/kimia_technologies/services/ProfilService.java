package com.kimia_technologies.services;

import com.kimia_technologies.dto.ProfilResponse;
import com.kimia_technologies.models.Profil;

import java.util.List;
import java.util.Optional;


public interface ProfilService {
    Profil create(Profil profil);
    List<Profil> findAll();
    Profil update(Profil profil);
    Optional<Profil> findById(Integer identifiant);
    ProfilResponse findByName(String name);
}
