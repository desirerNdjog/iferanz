package com.kimia_technologies.mapper;

import com.kimia_technologies.dto.UserResponse;
import com.kimia_technologies.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 04/09/2023
 */

@Service
public class UserResponseMapper implements Function<User, UserResponse> {
    @Override
    public UserResponse apply(User user) {
        return new UserResponse(
                user.getIdUser(),
                user.getEmail(),
                user.getProfil(),
                user.getPhone(),
                user.getPasswordResetToken(),
                user.getPasswordResetOtp(),
                user.getIsActive()
        );
    }
}
