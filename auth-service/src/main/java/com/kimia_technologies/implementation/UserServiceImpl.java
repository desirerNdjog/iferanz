package com.kimia_technologies.implementation;

import com.kimia_technologies.dto.UserResponse;
import com.kimia_technologies.mapper.UserResponseMapper;
import com.kimia_technologies.models.User;
import com.kimia_technologies.repositories.UserRepository;
import com.kimia_technologies.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserResponseMapper mapper;

    @Override
    public User create(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
       return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }


    @Override
    public User findByIdUser(Integer identifiant) {
       return null;
    }

    @Override
    public UserResponse findBypasswordResetOtp(String otp) {
        try{
            Optional<User> user = repository.findByPasswordResetOtp(otp);
            if (user != null){
                return user
                        .stream()
                        .map(mapper)
                        .collect(Collectors.toList())
                        .get(0);
            }
        }catch (IndexOutOfBoundsException exception){
            return null ;
        }
        return null;
    }

    @Override
    public UserResponse findByEmail(String email) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent()){
            return user.stream()
                    .map(mapper)
                    .collect(Collectors.toList())
                    .get(0);
        }
        return null;
    }

    @Override
    public UserResponse findByPhone(String phone) {
        Optional<User> user = repository.findByPhone(phone);
        if (user.isPresent()){
            return user.stream()
                    .map(mapper)
                    .collect(Collectors.toList())
                    .get(0);
        }
        return null;
    }

    @Override
    public User findUser(String phone) {
        Optional<User> user = repository.findByPhone(phone);
        if (user.isPresent()) return user.get();
        return null;
    }

}
