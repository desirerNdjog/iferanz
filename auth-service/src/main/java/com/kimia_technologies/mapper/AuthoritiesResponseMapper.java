package com.kimia_technologies.mapper;

import com.kimia_technologies.dto.AuthorityResponse;
import com.kimia_technologies.models.Authority;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 04/09/2023
 */
@Service
public class AuthoritiesResponseMapper implements Function<Authority, AuthorityResponse> {
    @Override
    public AuthorityResponse apply(Authority authority) {
        return new AuthorityResponse(
                authority.getIdAuthorities(),
                authority.getName(),
                authority.getDescription()
        );
    }
}
