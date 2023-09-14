package com.kimia_technologies.dao_service;

import com.kimia_technologies.dto.AdministorResponse;
import org.springframework.data.domain.Page;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 08/09/2023
 */
public interface AdministratorDaoService {
    Page<AdministorResponse> findAll(String element, int page, int size);
}
