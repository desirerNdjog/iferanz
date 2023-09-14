package com.kimia_technologies.controllers;

import com.kimia_technologies.dao_service.ProfilDaoService;
import com.kimia_technologies.dto.ProfilResponse;
import com.kimia_technologies.models.Profil;
import com.kimia_technologies.services.ProfilService;
import com.kimia_technologies.exceptions.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profils")
public class ProfilController {
    private final ProfilService service;
    private final ProfilDaoService profilDaoService;

    @GetMapping()
    public ResponseEntity<HttpResponse> findAll(@RequestParam Optional<String> name,
                                              @RequestParam Optional<Integer> page,
                                              @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of("pages", profilDaoService.findAll(name.orElse(""), page.orElse(0), size.orElse(6))))
                        .message("profils retreive")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping("/profil")
    public ResponseEntity<HttpResponse> crate(@RequestBody Profil profil){
        ProfilResponse response = service.findByName(profil.getName());
        if (response == null){
            service.create(profil);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("profil create")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build()
            );
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("Oops, profil existe")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build());
    }

    @PutMapping("/{idProfil}/profil")
    public ResponseEntity<HttpResponse> update(@PathVariable("idProfil") Integer idProfil, @RequestBody Profil profile){
        Optional<Profil> profil = service.findById(idProfil);
        if (profil.isPresent()){
            profil.get().setName(profile.getName());
            profil.get().setAuthorities(profile.getAuthorities());
            service.update(profil.get());
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("mis á jour effectué")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build());

        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("profil introuvable")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build()
        );
    }
}
