package com.kimia_technologies.init;

import com.kimia_technologies.dto.ProfilResponse;
import com.kimia_technologies.models.Profil;
import com.kimia_technologies.services.ProfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class InitProfil implements ApplicationRunner {
    private final ProfilService service;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (service.findAll().isEmpty()){

            ProfilResponse administrator = service.findByName("administrator");
            if (administrator == null) service.create(new Profil(null, "administrator", null ));

            ProfilResponse supervisor = service.findByName("supervisor");
            if (supervisor == null) service.create(new Profil(null, "supervisor", null ));

            ProfilResponse customer = service.findByName("costumer");
            if (customer == null) service.create(new Profil(null, "costumer", null ));

            ProfilResponse operator = service.findByName("operator");
            if (operator == null) service.create(new Profil(null, "operator", null ));

            ProfilResponse deliver = service.findByName("deliver");
            if (deliver == null) service.create(new Profil(null, "deliver", null ));

            ProfilResponse marchand = service.findByName("marchand");
            if (marchand == null) service.create(new Profil(null, "marchand", null ));
        }
    }
}
