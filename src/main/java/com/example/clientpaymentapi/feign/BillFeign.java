package com.example.clientpaymentapi.feign;

import com.example.clientpaymentapi.model.CompanyModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("bill-company-api")
public interface BillFeign {
    @GetMapping("/bill")
    CompanyModel getBillByByClientId(@RequestParam String clientId);
}
