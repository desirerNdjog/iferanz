package com.kimia_technologies.repositories;

import com.kimia_technologies.models.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Integer>, JpaRepository<Authority, Integer> {
    Optional<Authority> findByIdAuthorities(Integer identifiant);
    Optional<Authority> findByName(String name);
    Page<Authority> findByNameContaining(String name, Pageable pageable);
}
