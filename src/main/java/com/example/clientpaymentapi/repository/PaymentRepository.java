package com.example.clientpaymentapi.repository;

import com.example.clientpaymentapi.model.ResponseModel;
import org.apache.tomcat.jni.Local;
import org.elasticsearch.client.ElasticsearchClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Repository
public interface PaymentRepository extends ElasticsearchRepository<PaymentEntity, String> {
    // Page<PaymentEntity> getPaymentEntitiesByDateOfPayment(Date dateOfPayment, Pageable pageable);
    Page<PaymentEntity> countPaymentEntitiesByClientId(String clientId, Pageable pageable);

    Page<PaymentEntity> getPaymentEntitiesByReceiverId(String receiverId, Pageable pageable);

    void deletePaymentEntitiesByPaymentId(String paymentId);

    PaymentEntity getPaymentEntitiesByPaymentId(String paymentId);

    PaymentEntity getPaymentEntitiesByClientId(String clientId);

    List<PaymentEntity> getPaymentEntitiesBy();

    List<PaymentEntity> findByDateOfPaymentGreaterThanEqualAndDateOfPaymentLessThanEqualAndClientId(LocalDate fromDate, LocalDate toDate, String clientId);



}
