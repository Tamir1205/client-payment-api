package com.example.clientpaymentapi.service;

import com.example.clientpaymentapi.model.DetailedResponse;
import com.example.clientpaymentapi.model.RequestModel;
import com.example.clientpaymentapi.model.ResponseModel;
import com.example.clientpaymentapi.search.DTO;
import org.apache.tomcat.jni.Local;
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
//    Page<ResponseModel> getTotalAmountOfPaymentsByClientId(String clientId,Pageable pageable,Double amountOfPayment);

    void deletePaymentById(String paymentId);

    // Page<ResponseModel> getPaymentByDateOfPayment(Date dateOfPayment, Pageable pageable);
    Page<ResponseModel> countPaymentsByClientId(String clientId, Pageable pageable);

    Page<ResponseModel> getPaymentsByReceiverId(String receiverId, Pageable pageable);

    Page<ResponseModel> getPaymentsByRange(Date fromDate, Date toDate, String clientId, Pageable pageable);
}
