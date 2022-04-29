package com.example.clientpaymentapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class RequestModel {
    private String paymentId;
    private String payerId;
    private String receiverId;
    @NotNull
    private String paymentType;
    private String paymentDescription;
    @NotNull
    private Double amountOfPayment;
    private LocalDate dateOfPayment;
    private Date fromDate;
    private Date toDate;



}
