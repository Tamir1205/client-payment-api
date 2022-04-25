package com.example.clientpaymentapi.repository;

import org.apache.tomcat.jni.Local;
import org.elasticsearch.client.ElasticsearchClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends ElasticsearchRepository<PaymentEntity, String> {
    // Page<PaymentEntity> getPaymentEntitiesByDateOfPayment(Date dateOfPayment, Pageable pageable);
    Page<PaymentEntity> countPaymentEntitiesByPayerId(String payerId,Pageable pageable);
    Page<PaymentEntity> getPaymentEntitiesByReceiverId(String receiverId, Pageable pageable);

    void deletePaymentEntitiesByPaymentId(String paymentId);

    PaymentEntity getPaymentEntitiesByPaymentId(String paymentId);
    PaymentEntity getPaymentEntitiesByPayerId(String payerId);
    List<PaymentEntity> getPaymentEntitiesBy();
//    Page<PaymentEntity> getPaymentEntitiesByPayerIdAndFromDateBetween(Date fromDate, Date toDate, Pageable pageable,String PayerId);
}
