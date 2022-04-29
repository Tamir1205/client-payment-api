package com.example.clientpaymentapi.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.Date;

@Document(indexName = "client-payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
    @Id
    @Field(type= FieldType.Keyword)
    private String paymentId;
    @Field(type= FieldType.Keyword)
    private String payerId;
    @Field(type= FieldType.Keyword)
    private String receiverId;
    @Field(type= FieldType.Text)
    private String paymentType;
    @Field(type= FieldType.Text)
    private String paymentDescription;
    @Field(type= FieldType.Double)
    private Double amountOfPayment;
    @Field(type = FieldType.Date)
    private LocalDate dateOfPayment;
    @Field(type = FieldType.Date)
    private Date fromDate;
    @Field(type = FieldType.Date)
    private Date toDate;
}
