package com.example.clientpaymentapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {
    private String paymentId;
    private String clientId;
    private String receiverId;
    private String paymentType;
    private String paymentDescription;
    private Double amountOfPayment;
    private LocalDate dateOfPayment;
//    private Date toDate;
//    private Date fromDate;
}
