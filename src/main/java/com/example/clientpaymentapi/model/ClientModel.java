package com.example.clientpaymentapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {
    @NotNull

    private String clientId;

    private String clientName;

    private String clientLastName;

    @NotNull(message = "Email can not be empty")
    @Email(message = "Incorrect Email")
    private String clientEmail;
}
