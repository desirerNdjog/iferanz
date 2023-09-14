package com.kimia_technologies.controllers;


import com.kimia_technologies.auth.dto.PasswordReset;
import com.kimia_technologies.dao_service.UserDaoService;
import com.kimia_technologies.dto.UserResponse;
import com.kimia_technologies.models.User;
import com.kimia_technologies.services.UserService;
import com.kimia_technologies.exceptions.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private final UserService service;
    private final UserDaoService userDaoService;

    @GetMapping()
    public ResponseEntity<HttpResponse> findAll(@RequestParam Optional<String> search,
                                                @RequestParam Optional<Integer> page,
                                                @RequestParam Optional<Integer> size){

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of("result", userDaoService.findAll(search.orElse(""), page.orElse(0), size.orElse(6) )))
                        .message("user retreive")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build());
    }

    @GetMapping("/user")
    public ResponseEntity<HttpResponse> find(@RequestParam Optional<String> email,
                                             @RequestParam Optional<Integer> page,
                                             @RequestParam Optional<Integer> size){
        return null;
    }
    @GetMapping("/{otp}/user-otp")
    public ResponseEntity<HttpResponse> findBypasswordResetOtp(@PathVariable("otp") String otp){
        UserResponse response = service.findBypasswordResetOtp(otp);
        if (response != null){
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of("opt", response.getPasswordResetOtp()))
                            .message("user retreive")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .message("token invalide")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build());
    }

    @PostMapping("/user")
    public ResponseEntity<HttpResponse> create(@RequestBody User user){
        UserResponse response = service.findByEmail(user.getEmail());
        if (response == null){
            UserResponse utilisateur = service.findByPhone(user.getPhone());
            if (utilisateur == null){
                service.create(user);
                return ResponseEntity.ok().body(
                        HttpResponse.builder()
                                .timeStamp(now().toString())
                                .datas(Map.of())
                                .message("user created")
                                .statusCode(HttpStatus.OK.value())
                                .httpStatus(HttpStatus.OK)
                                .build());
            }return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("ce numéro est utilisé")
                            .statusCode(HttpStatus.FOUND.value())
                            .httpStatus(HttpStatus.FOUND)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("Oops, utilisateur existant")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build());
    }

    @PutMapping("/{identifiant}/user")
    public ResponseEntity<HttpResponse> update(@PathVariable("identifiant") Integer identifiant, @RequestBody User user){
        User userr = service.findByIdUser(identifiant);
        if (userr != null){
            userr.setEmail(user.getEmail());
            userr.setPassword(user.getPassword());
            userr.setProfil(user.getProfil());
            userr.setIsActive(user.getIsActive());
            service.update(user);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("user update")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("Oops, utilisateur introuvable")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build());
    }

    @PutMapping("/{email}/user-email")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email, @RequestBody PasswordReset passwordReset){
        User user = userDaoService.findByEmailOrPhone(email);
        if (user != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(passwordReset.getPassword()));
            service.update(user);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .message("user updated")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .message("user n'existe pas")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build());
    }

    @PutMapping("/{phone}/user-phone")
    public ResponseEntity<HttpResponse> resetPhonePassword(@PathVariable("phone") String phone, @RequestBody PasswordReset passwordReset){
        User user = userDaoService.findByEmailOrPhone(phone);
        if (user != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(passwordReset.getPassword()));
            service.update(user);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .message("user updated")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .message("user n'existe pas")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build());
    }


}
