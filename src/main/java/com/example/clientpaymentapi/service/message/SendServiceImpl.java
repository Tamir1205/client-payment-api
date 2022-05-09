package com.example.clientpaymentapi.service.message;


import com.example.clientpaymentapi.model.ModelBill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendServiceImpl implements SendService {
    private KafkaTemplate<Long, ModelBill> kafkaTemplate;

    private final ObjectMapper objectMapper;
    @Autowired
    public SendServiceImpl(KafkaTemplate<Long, ModelBill> kafkaTemplate,
                               ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public SendServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }



    @Override
    public ModelBill save(ModelBill modelBill) {
        return null;
    }

    @Override
    public ModelBill send(ModelBill modelBill) {
        log.info("<= sending {}", writeValueAsString(modelBill));
        kafkaTemplate.send("client-payment",modelBill);
        return modelBill;
    }

    private String writeValueAsString(ModelBill modelBill) {
        try {
            return objectMapper.writeValueAsString(modelBill);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + modelBill.toString());
        }
    }


}
