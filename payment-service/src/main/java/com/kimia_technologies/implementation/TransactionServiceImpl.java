package com.kimia_technologies.implementation;

import com.kimia_technologies.dto.Transaction;
import com.kimia_technologies.services.TransactionService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 06/09/2023
 */

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Value("${biapay.base-url}")
    private String biaPayBaseUrl ;
    @Override
    public HttpResponse<String> transaction(Transaction transaction) {
            HttpResponse<String> response = Unirest.post(biaPayBaseUrl +"/api/auth/login")
                    .header("Content-Type", "application/json")
                    .header("Cookie", "JSESSIONID=B4606E6C14310709555982DA06A45CA8")
                    .body(transaction)
                    .asString();
            return response;
    }
}
