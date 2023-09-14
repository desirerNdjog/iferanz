package com.kimia_technologies.services;

import com.kimia_technologies.dto.Transaction;
import kong.unirest.HttpResponse;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 06/09/2023
 */
public interface TransactionService {
    HttpResponse<String> transaction(Transaction transaction);
}
