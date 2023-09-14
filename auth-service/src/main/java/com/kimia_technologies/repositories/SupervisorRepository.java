package com.kimia_technologies.repositories;

import com.kimia_technologies.models.Supervisor;
import com.kimia_technologies.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 04/09/2023
 */

@Repository
public interface SupervisorRepository extends PagingAndSortingRepository<Supervisor, Integer>, JpaRepository<Supervisor, Integer> {
    Page<Supervisor> findByNameContaining(String name, Pageable pageable);
    Optional<Supervisor> findByIdSupervisor(Integer identifiant);
    Optional<Supervisor> findByUser(User user);

}
