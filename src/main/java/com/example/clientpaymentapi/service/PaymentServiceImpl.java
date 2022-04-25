package com.example.clientpaymentapi.service;

import com.example.clientpaymentapi.model.RequestModel;
import com.example.clientpaymentapi.model.ResponseModel;
import com.example.clientpaymentapi.repository.PaymentEntity;
import com.example.clientpaymentapi.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.data.elasticsearch.annotations.DateFormat.date;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
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
        paymentEntity=paymentRepository.save(paymentEntity);

        return modelMapper.map(paymentEntity, ResponseModel.class);
    }

    @Override
    public ResponseModel getPaymentById(String paymentId) {

        PaymentEntity paymentEntity = paymentRepository.getPaymentEntitiesByPaymentId(paymentId);

        return modelMapper.map(paymentEntity, ResponseModel.class);
    }

    @Override
    public ResponseModel getPaymentByPayerId(String payerId) {
        PaymentEntity paymentEntity= paymentRepository.getPaymentEntitiesByPayerId(payerId);
        return modelMapper.map(paymentEntity,ResponseModel.class);
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

//    @Override
//    public Page<ResponseModel> getPaymentByDateOfPayment(Date dateOfPayment, Pageable pageable) {
//   return paymentRepository.getPaymentEntitiesByDateOfPayment(dateOfPayment,pageable).
//           map(paymentEntity ->
//            modelMapper.map(paymentEntity,ResponseModel.class));
//
//    }


    @Override
    public Page<ResponseModel> countPaymentsByPayerId(String payerId, Pageable pageable) {
        return paymentRepository.countPaymentEntitiesByPayerId(payerId,pageable).
                map(paymentEntity -> modelMapper.map(paymentEntity,ResponseModel.class));
    }

    @Override
    public Page<ResponseModel> getPaymentsByReceiverId(String receiverId, Pageable pageable) {
        return paymentRepository.getPaymentEntitiesByReceiverId(receiverId, pageable).
                map(paymentEntity -> modelMapper.map(paymentEntity, ResponseModel.class));
    }

//    @Override
//    public Page<ResponseModel> getPaymentEntitiesByPayerIdAndFromDateBetween(Date fromDate, Date toDate, Pageable pageable, String payerId) {
//        return paymentRepository.getPaymentEntitiesByPayerIdAndFromDateBetween(fromDate,toDate,pageable,payerId).
//                map(paymentEntity -> modelMapper.map(paymentEntity,ResponseModel.class));
//    }


}
