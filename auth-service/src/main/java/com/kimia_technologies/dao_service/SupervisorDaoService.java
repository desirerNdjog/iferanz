package com.kimia_technologies.dao_service;

import com.kimia_technologies.dto.SupervisorResponse;
import org.springframework.data.domain.Page;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 08/09/2023
 */
public interface SupervisorDaoService {
    Page<SupervisorResponse> findAll(String elment, int page, int size);
}
