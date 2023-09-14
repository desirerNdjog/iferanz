package com.kimia_technologies.repositories;

import com.kimia_technologies.models.Administrator;
import com.kimia_technologies.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministratorRepository extends PagingAndSortingRepository<Administrator, Integer>, JpaRepository<Administrator, Integer> {
    Optional<Administrator> findByIdAdministrator(Integer identifiant);
    Page<Administrator> findByNameContaining(String name, Pageable pageable);
    Optional<Administrator> findByUser(User user);
}
