package com.kimia_technologies.services;

import com.kimia_technologies.dto.AuthorityResponse;
import com.kimia_technologies.models.Authority;
import org.springframework.data.domain.Page;


public interface AuthorityService {
    Authority create(Authority authority);
    AuthorityResponse findByName(String name);
}
