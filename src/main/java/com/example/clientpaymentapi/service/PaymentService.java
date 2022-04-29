package com.example.clientpaymentapi.service;

import com.example.clientpaymentapi.model.RequestModel;
import com.example.clientpaymentapi.model.ResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PaymentService {
    ResponseModel createPayment(RequestModel requestModel);

    ResponseModel updatePayment(RequestModel requestModel);

    ResponseModel getPaymentById(String paymentId);

    ResponseModel getPaymentByPayerId(String payerId);

    List<ResponseModel> getAllPayments();

    void deletePaymentById(String paymentId);

    // Page<ResponseModel> getPaymentByDateOfPayment(Date dateOfPayment, Pageable pageable);
    Page<ResponseModel> countPaymentsByPayerId(String payerId, Pageable pageable);

    Page<ResponseModel> getPaymentsByReceiverId(String receiverId, Pageable pageable);

    Page<ResponseModel> getPaymentsByRange(Date fromDate,Date toDate,String payerId,Pageable pageable);
}
