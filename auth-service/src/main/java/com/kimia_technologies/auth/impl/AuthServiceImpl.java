package com.kimia_technologies.auth.impl;

import com.kimia_technologies.auth.dto.LoginRequest;
import com.kimia_technologies.auth.dto.RecoverEmail;
import com.kimia_technologies.auth.dto.RecoverPhone;
import com.kimia_technologies.auth.dto.RegisterRequest;
import com.kimia_technologies.auth.services.AuthService;
import com.kimia_technologies.dao_service.UserDaoService;
import com.kimia_technologies.securities.JwtService;
import com.kimia_technologies.exceptions.HttpResponse;
import com.kimia_technologies.models.User;
import com.kimia_technologies.services.JavaSendMailService;
import com.kimia_technologies.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import static java.time.LocalTime.now;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 05/09/2023
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserDaoService userDaoService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JavaSendMailService javaSendMailService;
    private final JwtService jwtService;

    @Override
    public String authentication(LoginRequest loginRequest) {
        User user = userDaoService.findByEmailOrPhone(loginRequest.getEmailOrPhone());
        if (user != null){
            try{
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmailOrPhone(),
                                loginRequest.getPassword()
                        )
                );
                Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                user.getProfil().getAuthorities().forEach(authority -> {
                    authorities.add(new SimpleGrantedAuthority(authority.getName()));
                });
                var token = jwtService.generateToken(user, authorities);
                return token;
            }catch (BadCredentialsException exception){
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .message("Bad Credentials")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .datas(Map.of())
                        .build();
            }
        }
        return null;
    }


    @Override
    public void register(RegisterRequest registerRequest) {
        User user = userService.findUser(registerRequest.getPhone());
        if (user != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(registerRequest.getPassword());
            registerRequest.setPassword(password);
            // TODO je dois resourdre ca...
        }
        userService.create(null);
    }

    @Override
    public String passwordRecoverEmail(User user) {
        try {
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            user.getProfil().getAuthorities().forEach(authority -> {
                authorities.add(new SimpleGrantedAuthority(authority.getName()));
            });
            var token = jwtService.generateTokentPassword(user, authorities);
            user.setPasswordResetToken(token);
            userService.update(user);
            javaSendMailService.sendEmail(user.getEmail(), token);
            return token;
        } catch (Exception exception) {
            HttpResponse.builder()
                    .timeStamp(now().toString())
                    .message("Oops, une erreur c'est produite")
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .datas(Map.of())
                    .build();
        }
        return null;
    }

    @Override
    public String passwordRecoverPhone(User user) {
        try {
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            user.getProfil().getAuthorities().forEach(authority -> {
                authorities.add(new SimpleGrantedAuthority(authority.getName()));
            });
            var token = jwtService.generateTokentPassword(user, authorities);
            String otp = this.generateOTPCode();
            user.setPasswordResetOtp(otp);
            user.setPasswordResetToken(token);
            userService.update(user);
            //envoyer le token via Twillio
            return token;
        } catch (Exception exception) {
            HttpResponse.builder()
                    .timeStamp(now().toString())
                    .message("Oops, une erreur c'est produite")
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .datas(Map.of())
                    .build();
        }
        return null;
    }

    private String generateOTPCode () {
        String result = "";
        int lenght = 5;
        int rang;
        for (int i = 0; i < lenght; i++) {
            // Generate random digit within 0-9
            rang = new Random().nextInt(9);
            result = result.concat(Integer.toString(rang));
        }
        return result;
    }
}
