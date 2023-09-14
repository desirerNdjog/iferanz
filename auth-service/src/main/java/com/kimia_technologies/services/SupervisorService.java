package com.kimia_technologies.services;

import com.kimia_technologies.dto.SupervisorResponse;
import com.kimia_technologies.models.Supervisor;
import com.kimia_technologies.models.User;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 04/09/2023
 */
public interface SupervisorService {
    Supervisor create(Supervisor supervisor);
    Supervisor update(Supervisor supervisor);
    Supervisor findById(Integer identifiant);
    SupervisorResponse findByUser(User user);
    void delete(Supervisor supervisor);
}
