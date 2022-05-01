package com.example.clientpaymentapi.controller;

import com.example.clientpaymentapi.feign.ClientFeign;
import com.example.clientpaymentapi.model.DetailedResponse;
import com.example.clientpaymentapi.model.RequestModel;
import com.example.clientpaymentapi.model.ResponseModel;
import com.example.clientpaymentapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    static {
    }

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ClientFeign clientFeign;

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
    public ResponseModel getPaymentByPayerId(@RequestParam String clientId) {
        return paymentService.getPaymentByClientId(clientId);
    }
    @GetMapping("/detail/{id}")
    public DetailedResponse getDetailPaymentById(@PathVariable String id){
    DetailedResponse detailedResponse=new DetailedResponse();
    ResponseModel responseModel=paymentService.getPaymentById(id);
    detailedResponse.setPaymentId(responseModel.getPaymentId());
    detailedResponse.setClient(clientFeign.getClientById(responseModel.getClientId()));
    detailedResponse.setDateOfPayment(responseModel.getDateOfPayment());
    detailedResponse.setPaymentType(responseModel.getPaymentType());
    detailedResponse.setReceiverId(responseModel.getReceiverId());
    detailedResponse.setPaymentDescription(responseModel.getPaymentDescription());
    detailedResponse.setAmountOfPayment(responseModel.getAmountOfPayment());
    return detailedResponse;
    }

//    @GetMapping("/date")
//    public Page<ResponseModel> getPaymentByDateOfPayment(@RequestParam Date dateOfPayment, Pageable pageable) {
//        return paymentService.getPaymentByDateOfPayment(dateOfPayment, pageable);
//    }

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

    @GetMapping("/range/{fromDate}/{toDate}")
    public Page<ResponseModel> getPaymentsByRangeOfDates(@RequestParam String payerId, Pageable pageable,
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable Date fromDate,
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable Date toDate) {

        return paymentService.getPaymentsByRange(fromDate, toDate, payerId, pageable);
    }

//    private static RangeQueryBuilder getQueryBuilder(final String payerId, final Date fromDate, final Date toDate){
//        return QueryBuilders.rangeQuery(payerId).gte(fromDate);
//    }
//    @GetMapping("/{fromDate}/{toDate}")
//    public Page<ResponseModel> getPaymentsBetweenTwoDates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd")Date toDate, @RequestParam String payerId, Pageable pageable){
//        return paymentService.getPaymentEntitiesByPayerIdAndFromDateBetween(fromDate,toDate,pageable,payerId);
//    }


}
