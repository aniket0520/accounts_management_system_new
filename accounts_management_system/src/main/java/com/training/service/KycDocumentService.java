package com.training.service;

import com.training.model.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class KycDocumentService {

    private final WebClient customersWebClient;

    @Autowired
    public KycDocumentService(WebClient customersWebClient) {
        this.customersWebClient = customersWebClient;
    }

    public String getCustomerIdByDocNumber(String documentNumber) {
        CustomerResponse customerResponse = customersWebClient.get()
                .uri("cif/" + documentNumber)
                .retrieve()
                .bodyToMono(CustomerResponse.class)
                .block();
        return  customerResponse.getCustomerId();
    }
}