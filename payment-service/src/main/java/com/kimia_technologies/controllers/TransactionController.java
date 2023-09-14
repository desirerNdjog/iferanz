package com.kimia_technologies.controllers;

import com.kimia_technologies.dto.Transaction;
import com.kimia_technologies.exceptions.HttpResponse;
import com.kimia_technologies.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 06/09/2023
 */

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping()
    public ResponseEntity<HttpResponse> transaction(@RequestBody Transaction transaction){
        kong.unirest.HttpResponse<String> response =  service.transaction(transaction);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .datas(Map.of("result", response.getBody()))
                        .httpStatus(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/test")
    public ResponseEntity<HttpResponse> test(){
        String test ="je fais le test";
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .httpStatus(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .datas(Map.of("result", test))
                        .build()
        );
    }
}
