package com.example.clientpaymentapi.service;

import com.example.clientpaymentapi.model.RequestModel;
import com.example.clientpaymentapi.model.ResponseModel;
import com.example.clientpaymentapi.repository.PaymentEntity;
import com.example.clientpaymentapi.repository.PaymentRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final RestHighLevelClient client;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    public PaymentServiceImpl(RestHighLevelClient client){
        this.client=client;
    }
    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }


    @Override
    public ResponseModel createPayment(RequestModel requestModel) {
        requestModel.setPaymentId(UUID.randomUUID().toString());
        LocalDate date = LocalDate.now();
        requestModel.setDateOfPayment(date);
        PaymentEntity paymentEntity = modelMapper.map(requestModel, PaymentEntity.class);
        paymentRepository.save(paymentEntity);
        return modelMapper.map(paymentEntity, ResponseModel.class);
    }

    @Override
    public ResponseModel updatePayment(RequestModel requestModel) {
        PaymentEntity paymentEntity = modelMapper.map(requestModel, PaymentEntity.class);
        PaymentEntity dbEntity = paymentRepository.getPaymentEntitiesByPaymentId(paymentEntity.getPaymentId());
        paymentEntity.setPaymentId(dbEntity.getPaymentId());
        paymentEntity = paymentRepository.save(paymentEntity);

        return modelMapper.map(paymentEntity, ResponseModel.class);
    }

    @Override
    public ResponseModel getPaymentById(String paymentId) {

        PaymentEntity paymentEntity = paymentRepository.getPaymentEntitiesByPaymentId(paymentId);

        return modelMapper.map(paymentEntity, ResponseModel.class);
    }

    @Override
    public ResponseModel getPaymentByClientId(String clientId) {
        PaymentEntity paymentEntity = paymentRepository.getPaymentEntitiesByClientId(clientId);
        return modelMapper.map(paymentEntity, ResponseModel.class);
    }

    @Override
    public List<ResponseModel> getAllPayments() {

        return paymentRepository.getPaymentEntitiesBy().stream().map(paymentEntity ->
                modelMapper.map(paymentEntity, ResponseModel.class)).collect(Collectors.toList());
    }



    @Override
    public void deletePaymentById(String paymentId) {
        paymentRepository.deletePaymentEntitiesByPaymentId(paymentId);
    }



    @Override
    public Page<ResponseModel> countPaymentsByClientId(String clientId, Pageable pageable) {
        paymentRepository.count();
        return paymentRepository.countPaymentEntitiesByClientId(clientId, pageable).
                map(paymentEntity -> modelMapper.map(paymentEntity, ResponseModel.class));
    }

    @Override
    public Page<ResponseModel> getPaymentsByReceiverId(String receiverId, Pageable pageable) {
        return paymentRepository.getPaymentEntitiesByReceiverId(receiverId, pageable).
                map(paymentEntity -> modelMapper.map(paymentEntity, ResponseModel.class));
    }

    @Override
    public List<PaymentEntity> getPaymentsByRange(LocalDate fromDate, LocalDate toDate, String clientId) {
        return paymentRepository.findByDateOfPaymentGreaterThanEqualAndDateOfPaymentLessThanEqualAndClientId(fromDate, toDate, clientId);
    }




}
