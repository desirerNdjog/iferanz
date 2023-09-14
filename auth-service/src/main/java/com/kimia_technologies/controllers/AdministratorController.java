package com.kimia_technologies.controllers;

import com.kimia_technologies.dao_service.AdministratorDaoService;
import com.kimia_technologies.dto.AdministorResponse;
import com.kimia_technologies.exceptions.HttpResponse;
import com.kimia_technologies.models.Administrator;
import com.kimia_technologies.services.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
public class AdministratorController {
    private final AdministratorService service;
    private final AdministratorDaoService administratorDaoService;

    @GetMapping()
    public ResponseEntity<HttpResponse> findAll(@RequestParam Optional<String> name,
                                                @RequestParam Optional<Integer> page,
                                                @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of("pages", administratorDaoService.findAll(name.orElse(""), page.orElse(0), size.orElse(6))))
                        .message("retreive administrator")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build());

    }

    @PostMapping("/admin")
    public ResponseEntity<HttpResponse> create(@RequestBody Administrator administrator){
        AdministorResponse response = service.findByUser(administrator.getUser());
        if (response == null){
            service.create(administrator);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("create administrator")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("ce compte est déja pris ")
                        .statusCode(HttpStatus.FOUND.value())
                        .httpStatus(HttpStatus.FOUND)
                        .build());

    }

    @PutMapping("/{identifiant}/admin")
    public ResponseEntity<HttpResponse> create(@PathVariable("identifiant") Integer identifiant, @RequestBody Administrator administrator){
        Administrator administratore = service.findByIdAdministrator(identifiant);
        if (administratore != null){
            administratore.setUser(administrator.getUser());
            administratore.setSexe(administrator.getSexe());
            administratore.setPhone(administrator.getPhone());
            administratore.setName(administrator.getName());
            administratore.setImage(administrator.getImage());
            service.update(administratore);
            service.create(administrator);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("update administrator")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("administrator inexistant")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build());

    }

    @DeleteMapping("/{identifiant}/admin")
    public ResponseEntity<HttpResponse> delete(@PathVariable("identifiant") Integer identifiant){
        Administrator administrator = service.findByIdAdministrator(identifiant);
        if (administrator != null){
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("delete administrator")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("administrator inexistant")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build());
    }
}
