package com.kimia_technologies.implementation;

import com.kimia_technologies.dto.ProfilResponse;
import com.kimia_technologies.mapper.ProfilResponseMapper;
import com.kimia_technologies.models.Profil;
import com.kimia_technologies.repositories.ProfilRepository;
import com.kimia_technologies.services.ProfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author  Desire Junior NDJOG
 * @version 1.0
 * @since
 */


@RequiredArgsConstructor
@Service
public class ProfilServiceImpl implements ProfilService {
    private final ProfilRepository repository;
    private final ProfilResponseMapper mapper;

    @Override
    public Profil create(Profil profil) {
        return repository.save(profil);
    }

    @Override
    public List<Profil> findAll() {
        return repository.findAll();
    }

    @Override
    public Profil update(Profil profil) {
        return repository.save(profil);
    }

    @Override
    public Optional<Profil> findById(Integer identifiant) {
        Optional<Profil> profil = repository.findByIdProfil(identifiant);
        if (profil.isPresent()) return profil;
        return null;
    }

    @Override
    public ProfilResponse findByName(String name) {
        try{
            ProfilResponse response = repository.findByName(name)
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList())
                    .get(0);
            if (response != null) return response;
        }catch (IndexOutOfBoundsException exception){
            return null;
        }
        return new ProfilResponse();
    }

}
