package com.example.clientpaymentapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelBill {
    private String paymentId;
    private CompanyModel CompanyModel;
    private String paymentType;
    private Double amountOfPayment;
    private Double remainderOfDebt;
    private ClientModel clientModel;
}
