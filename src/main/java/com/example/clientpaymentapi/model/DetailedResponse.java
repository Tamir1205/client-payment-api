package com.example.clientpaymentapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedResponse {
    private String paymentId;
    private ClientModel Client;
    private String receiverId;
    private String paymentType;
    private String paymentDescription;
    private Double amountOfPayment;
    private LocalDate dateOfPayment;

}
