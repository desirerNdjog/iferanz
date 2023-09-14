package com.kimia_technologies.implementation;

import com.kimia_technologies.dto.AuthorityResponse;
import com.kimia_technologies.mapper.AuthoritiesResponseMapper;
import com.kimia_technologies.models.Authority;
import com.kimia_technologies.repositories.AuthorityRepository;
import com.kimia_technologies.services.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository repository;
    private final AuthoritiesResponseMapper mapper;


    @Override
    public Authority create(Authority authority) {
        return repository.save(authority);
    }

    @Override
    public AuthorityResponse findByName(String name){
        try{
            AuthorityResponse response = repository.findByName(name)
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList())
                    .get(0);
            if (response != null) return response;
        }catch (IndexOutOfBoundsException exception){
            return null;
        }
        return new AuthorityResponse();
    }

}
