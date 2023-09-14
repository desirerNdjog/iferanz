package com.kimia_technologies.controllers;

import com.kimia_technologies.dao_service.SupervisorDaoService;
import com.kimia_technologies.dto.SupervisorResponse;
import com.kimia_technologies.exceptions.HttpResponse;
import com.kimia_technologies.models.Supervisor;
import com.kimia_technologies.services.SupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 04/09/2023
 */

@RestController
@RequestMapping("/api/v1/supervisors")
@RequiredArgsConstructor
public class SupervisorController {
    private final SupervisorService service;
    private final SupervisorDaoService supervisorDaoService;

    @GetMapping()
    public ResponseEntity<HttpResponse> findAll(@RequestParam Optional<String> name,
                                                @RequestParam Optional<Integer> page,
                                                @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of("pages", supervisorDaoService.findAll(name.orElse(""), page.orElse(0), size.orElse(6) )))
                        .message("supervisor retreive")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build());
    }

    @PostMapping("/supervisor")
    public ResponseEntity<HttpResponse> create(@RequestBody Supervisor supervisor){
        SupervisorResponse response = service.findByUser(supervisor.getUser());
        if (response == null){
            service.create(supervisor);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("user retreive")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("ce compte est déjà pris")
                        .statusCode(HttpStatus.FOUND.value())
                        .httpStatus(HttpStatus.FOUND)
                        .build());
    }

    @PutMapping("/{identifiant}/supervisor")
    public ResponseEntity<HttpResponse> update(@PathVariable("identifiant") Integer identifiant, @RequestBody Supervisor supervisor){
        Supervisor supervisore = service.findById(identifiant);
        if (supervisore != null){
            supervisore.setSexe(supervisor.getSexe());
            supervisore.setName(supervisore.getName());
            supervisore.setPhone(supervisor.getPhone());
            supervisore.setImage(supervisor.getImage());
            service.update(supervisore);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("supervisor update")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("Oops, supervisor introuvable")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build());
    }

    @DeleteMapping("/{identifiant}/supervisor")
    public ResponseEntity<HttpResponse> update(@PathVariable("identifiant") Integer identifiant){
        Supervisor supervisor = service.findById(identifiant);
        if (supervisor != null){
            service.delete(supervisor);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("supervisor delete")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("Oops, supervisor introuvable")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build());
    }
}
