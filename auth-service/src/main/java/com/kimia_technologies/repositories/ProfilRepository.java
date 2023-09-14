package com.kimia_technologies.repositories;

import com.kimia_technologies.models.Profil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilRepository extends PagingAndSortingRepository<Profil, Integer>, JpaRepository<Profil, Integer> {
    Page<Profil> findByNameContaining(String name, Pageable pageable);
    Optional<Profil> findByName(String name);
    Optional<Profil> findByIdProfil(Integer identifiant);
}
