package com.kimia_technologies.controllers;

import com.kimia_technologies.dao_service.AuthoritiesDaoService;
import com.kimia_technologies.dto.AuthorityResponse;
import com.kimia_technologies.models.Authority;
import com.kimia_technologies.services.AuthorityService;
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
@RequestMapping("/api/v1")
public class AuthoritoryController {
    private final AuthorityService service;
    private final AuthoritiesDaoService authoritiesDaoService;
    @GetMapping("/authorities")
    public ResponseEntity<HttpResponse> findAll(@RequestParam Optional<String> name,
                                                @RequestParam Optional<Integer> page,
                                                @RequestParam Optional<Integer> size){

       return ResponseEntity.ok().body(
               HttpResponse.builder()
                       .timeStamp(now().toString())
                       .datas(Map.of("pages", authoritiesDaoService.findAll(name.orElse(""), page.orElse(0), size.orElse(6))))
                       .message("retreive authorities")
                       .statusCode(HttpStatus.OK.value())
                       .httpStatus(HttpStatus.OK)
                       .build()
       );
    }

    @PostMapping("/authority")
    public ResponseEntity<HttpResponse> create(@RequestBody Authority authority){
        AuthorityResponse response = service.findByName(authority.getName());
        if (response == null){
            service.create(authority);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .datas(Map.of())
                            .message("create authority")
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .build()
            );
        }
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of())
                        .message("Oops, authority existe")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build()
        );
    }

}
