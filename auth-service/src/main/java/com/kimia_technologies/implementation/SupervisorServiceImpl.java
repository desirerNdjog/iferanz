package com.kimia_technologies.implementation;

import com.kimia_technologies.dto.SupervisorResponse;
import com.kimia_technologies.mapper.SupervisorResponseMapper;
import com.kimia_technologies.models.Supervisor;
import com.kimia_technologies.models.User;
import com.kimia_technologies.repositories.SupervisorRepository;
import com.kimia_technologies.services.SupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 04/09/2023
 */

@Service
@RequiredArgsConstructor
public class SupervisorServiceImpl implements SupervisorService {
    private final SupervisorRepository repository;
    private final SupervisorResponseMapper mapper;

    @Override
    public Supervisor create(Supervisor supervisor) {
        return repository.save(supervisor);
    }

    @Override
    public Supervisor update(Supervisor supervisor) {
        return repository.save(supervisor);
    }

    @Override
    public Supervisor findById(Integer identifiant) {
        Optional<Supervisor> supervisor = repository.findByIdSupervisor(identifiant);
        if (supervisor.isPresent()) return supervisor.get();
        return null;
    }

    @Override
    public SupervisorResponse findByUser(User user) {
        try{
            SupervisorResponse response = repository.findByUser(user).stream()
                    .map(mapper)
                    .collect(Collectors.toList())
                    .get(0);
            if (response != null) return response;
        }catch (IndexOutOfBoundsException exception){
            return null;
        }
        return new SupervisorResponse();
    }

    @Override
    public void delete(Supervisor supervisor) {
        repository.delete(supervisor);
    }
}
