package com.kimia_technologies.auth.controllers;

import com.kimia_technologies.auth.dto.LoginRequest;
import com.kimia_technologies.auth.dto.RecoverEmail;
import com.kimia_technologies.auth.dto.RecoverPhone;
import com.kimia_technologies.auth.dto.RegisterRequest;
import com.kimia_technologies.auth.services.AuthService;
import com.kimia_technologies.dao_service.UserDaoService;
import com.kimia_technologies.dto.UserResponse;
import com.kimia_technologies.exceptions.HttpResponse;
import com.kimia_technologies.models.User;
import com.kimia_technologies.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.time.LocalDateTime.now;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 05/09/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserDaoService userDaoService;

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody LoginRequest loginRequest){
        String token = authService.authentication(loginRequest);
        if (token != null){
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .datas(Map.of("acces_token", token))
                            .message("utilisateur connecté")
                            .build()
            );
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .message("utilisateur introuvable")
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> register(RegisterRequest registerRequest){
        UserResponse userResponse = userService.findByPhone(registerRequest.getPhone());
        if (userResponse == null){
            authService.register(registerRequest);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .message("created")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build()
            );
        }
        return null;
    }

    @PostMapping("/recover-password-email")
    public ResponseEntity<HttpResponse> recoverByEmail(@RequestBody RecoverEmail recoverEmail){
        User user = userDaoService.findByEmailOrPhone(recoverEmail.getEmail());
        if (user != null){
            String token = authService.passwordRecoverEmail(user);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .message("Consulter votre boite mail, un mail vous a été envoyé")
                            .datas(Map.of("access_token", token))
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build()
            );

        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .message("Cet utilisateur n'existe pas")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping("/recover-password-phone")
    public ResponseEntity<HttpResponse> recoverByPhone(@RequestBody RecoverPhone recoverPhone){
        User user = userDaoService.findByEmailOrPhone(recoverPhone.getPhone());
        if (user != null){
            String token = authService.passwordRecoverPhone(user);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .message("OTP envoyé")
                            .datas(Map.of("acces_token", token))
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build()
            );
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .message("Cet utilisateur n'existe pas")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build()
        );
    }
}
