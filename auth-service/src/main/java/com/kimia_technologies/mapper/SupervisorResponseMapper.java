package com.kimia_technologies.mapper;

import com.kimia_technologies.dto.SupervisorResponse;
import com.kimia_technologies.models.Supervisor;
import org.springframework.stereotype.Service;

import java.util.function.Function;


/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 04/09/2023
 */

@Service
public class SupervisorResponseMapper implements Function<Supervisor, SupervisorResponse> {

    @Override
    public SupervisorResponse apply(Supervisor supervisor) {
        return new SupervisorResponse(
            supervisor.getIdSupervisor(),
                supervisor.getName(),
                supervisor.getPhone(),
                supervisor.getImage(),
                supervisor.getSexe(),
                supervisor.getUser()
        );
    }
}
