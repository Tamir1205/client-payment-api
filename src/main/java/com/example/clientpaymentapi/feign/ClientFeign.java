package com.example.clientpaymentapi.feign;

import com.example.clientpaymentapi.model.ClientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("client-core-api")
public interface ClientFeign {
    @GetMapping("/client")
    ClientModel getClientById(@RequestParam String clientId);
}
