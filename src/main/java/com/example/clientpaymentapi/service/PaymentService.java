package com.example.clientpaymentapi.service;

import com.example.clientpaymentapi.model.DetailedResponse;
import com.example.clientpaymentapi.model.RequestModel;
import com.example.clientpaymentapi.model.ResponseModel;
import com.example.clientpaymentapi.repository.PaymentEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PaymentService {
    ResponseModel createPayment(RequestModel requestModel);

    ResponseModel updatePayment(RequestModel requestModel);

    ResponseModel getPaymentById(String paymentId);

    ResponseModel getPaymentByClientId(String clientId);

    List<ResponseModel> getAllPayments();

    void deletePaymentById(String paymentId);

    Page<ResponseModel> countPaymentsByClientId(String clientId, Pageable pageable);

    Page<ResponseModel> getPaymentsByReceiverId(String receiverId, Pageable pageable);

    List<PaymentEntity> getPaymentsByRange(LocalDate fromDate, LocalDate toDate, String clientId);
}
