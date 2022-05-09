package com.example.clientpaymentapi.controller;

import com.example.clientpaymentapi.feign.BillFeign;
import com.example.clientpaymentapi.feign.ClientFeign;
import com.example.clientpaymentapi.model.*;
import com.example.clientpaymentapi.repository.PaymentEntity;
import com.example.clientpaymentapi.service.PaymentService;
import com.example.clientpaymentapi.service.message.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    //    @Autowired
//    private KafkaTemplate<String, ModelBill> kafkaTemplate;
    private final SendService service;

    public PaymentController(SendService service) {
        this.service = service;
    }

//    @PostMapping
//    public void send(@RequestBody ModelBill modelBill) {
//        service.send(modelBill);
//    }


    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ClientFeign clientFeign;

    @Autowired
    private BillFeign billFeign;

    @PostMapping
    public ResponseModel createPayment(@RequestBody RequestModel requestModel) {
        return paymentService.createPayment(requestModel);
    }

    @PutMapping
    public ResponseModel updatePayment(@RequestParam String paymentId, @RequestBody RequestModel requestModel) {
        requestModel.setPaymentId(paymentId);
        return paymentService.updatePayment(requestModel);
    }

    @DeleteMapping
    public void deletePayment(@RequestParam String paymentId) {
        paymentService.deletePaymentById(paymentId);
    }

    @GetMapping("/{paymentId}")
    public ResponseModel getPaymentById(@PathVariable String paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping
    public ResponseModel getPaymentByClientId(@RequestParam String clientId) {
        return paymentService.getPaymentByClientId(clientId);
    }

    @GetMapping("/detail/{id}")
    public DetailedResponse getDetailPaymentById(@PathVariable String id) {
        DetailedResponse detailedResponse = new DetailedResponse();
        ResponseModel responseModel = paymentService.getPaymentById(id);
        detailedResponse.setPaymentId(responseModel.getPaymentId());
        detailedResponse.setClient(clientFeign.getClientById(responseModel.getClientId()));
        detailedResponse.setDateOfPayment(responseModel.getDateOfPayment());
        detailedResponse.setPaymentType(responseModel.getPaymentType());
        detailedResponse.setReceiverId(responseModel.getReceiverId());
        detailedResponse.setPaymentDescription(responseModel.getPaymentDescription());
        detailedResponse.setAmountOfPayment(responseModel.getAmountOfPayment());
        return detailedResponse;

    }


    @GetMapping("/receiver")
    public Page<ResponseModel> getPaymentByReceiverId(@RequestParam String receiverId, Pageable pageable) {
        return paymentService.getPaymentsByReceiverId(receiverId, pageable);
    }

    @GetMapping("/count")
    public Page<ResponseModel> countPaymentByPayerId(@RequestParam String clientId, Pageable pageable) {
        return paymentService.countPaymentsByClientId(clientId, pageable);
    }

    @GetMapping("/all")
    public List<ResponseModel> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/range/{fromDate}/{toDate}/{clientId}")
    public List<PaymentEntity> getPaymentsByRangeOfDates(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate fromDate,
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate toDate,
                                                         @PathVariable String clientId) {

        return paymentService.getPaymentsByRange(fromDate, toDate, clientId);
    }

    @GetMapping("/billDetailed/{paymentId}")
    public ModelBill getNewModel(@PathVariable String paymentId) {
        ModelBill ModelBill = new ModelBill();
        ResponseModel responseModel = paymentService.getPaymentById(paymentId);
        ModelBill.setPaymentId(responseModel.getPaymentId());
        ModelBill.setCompanyModel(billFeign.getBillByByClientId(responseModel.getClientId()));
        ModelBill.setClientModel(clientFeign.getClientById(responseModel.getClientId()));
        ModelBill.setPaymentType(responseModel.getPaymentType());
        ModelBill.setAmountOfPayment(responseModel.getAmountOfPayment());
        ModelBill.setRemainderOfDebt(ModelBill.getCompanyModel().getAmountOfDebt() - ModelBill.getAmountOfPayment());
        return service.send(ModelBill);
    }


}
