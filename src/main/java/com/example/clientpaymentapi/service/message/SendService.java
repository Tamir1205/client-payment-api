package com.example.clientpaymentapi.service.message;

import com.example.clientpaymentapi.model.ModelBill;

public interface SendService {
    ModelBill save(ModelBill modelBill);
    ModelBill send(ModelBill modelBill);
}
