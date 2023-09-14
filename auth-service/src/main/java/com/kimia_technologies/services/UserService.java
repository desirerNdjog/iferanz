package com.kimia_technologies.services;

import com.kimia_technologies.dto.UserResponse;
import com.kimia_technologies.models.User;


public interface UserService {
    User create(User user);
    User update(User user);
    User findByIdUser(Integer identifiant);
    UserResponse findBypasswordResetOtp(String otp);
    UserResponse findByEmail(String email);
    UserResponse findByPhone(String phone);
    User findUser(String phone);
}
