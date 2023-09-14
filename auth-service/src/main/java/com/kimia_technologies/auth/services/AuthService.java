package com.kimia_technologies.auth.services;

import com.kimia_technologies.auth.dto.LoginRequest;
import com.kimia_technologies.auth.dto.RecoverPhone;
import com.kimia_technologies.auth.dto.RegisterRequest;
import com.kimia_technologies.models.User;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 05/09/2023
 */
public interface AuthService {
    String authentication(LoginRequest loginRequest);
    void register(RegisterRequest registerRequest);
    String passwordRecoverEmail(User user);
    String passwordRecoverPhone(User user);


}
