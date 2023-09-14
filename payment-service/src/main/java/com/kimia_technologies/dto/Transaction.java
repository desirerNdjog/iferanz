package com.kimia_technologies.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 06/09/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Transaction implements Serializable {
    private String clientId;
    private String clientSecret;
    private ClientDetails clientDetails;
    private ClientUserDetails clientUserDetails;
    private OrderDetails orderDetails;
    private TransactionDetails transactionDetails;
}
