package com.kimia_technologies.services;

import com.kimia_technologies.dto.AdministorResponse;
import com.kimia_technologies.models.Administrator;
import com.kimia_technologies.models.User;

public interface AdministratorService {
    Administrator create(Administrator administrator);
    Administrator update(Administrator administrator);
    Administrator findByIdAdministrator(Integer identifiant);
    AdministorResponse findByUser(User user);
    void delete(Administrator administrator);
}
