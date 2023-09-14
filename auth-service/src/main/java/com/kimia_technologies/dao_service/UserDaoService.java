package com.kimia_technologies.dao_service;

import com.kimia_technologies.dto.UserResponse;
import com.kimia_technologies.models.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 08/09/2023
 */

@Service
public interface UserDaoService {
    Page<UserResponse> findAll(String element, int page, int size);
    User findByEmailOrPhone(String element);
}
