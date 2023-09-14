package com.kimia_technologies.implementation;

import com.kimia_technologies.dto.AdministorResponse;
import com.kimia_technologies.mapper.AdministratorServiceMapper;
import com.kimia_technologies.models.Administrator;
import com.kimia_technologies.models.User;
import com.kimia_technologies.repositories.AdministratorRepository;
import com.kimia_technologies.services.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository repository;
    private final AdministratorServiceMapper mapper;

    @Override
    public Administrator create(Administrator administrator) {
        return repository.save(administrator);
    }

    @Override
    public Administrator update(Administrator administrator) {
        return repository.save(administrator);
    }

    @Override
    public Administrator findByIdAdministrator(Integer identifiant) {
        Optional<Administrator> administrator = repository.findByIdAdministrator(identifiant);
        if (administrator.isPresent()){
            return administrator.get();
        }
        return null;
    }

    @Override
    public AdministorResponse findByUser(User user) {
        try{
            AdministorResponse response = repository.findByUser(user)
                    .stream()
                    .map(mapper)
                    .collect(Collectors.toList())
                    .get(0);
            if (response != null) return response;
        }catch (IndexOutOfBoundsException exception){
            return null;
        }
        return null;
    }

    @Override
    public void delete(Administrator administrator) {
        repository.delete(administrator);
    }
}
