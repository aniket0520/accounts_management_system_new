package com.training.service;

import com.training.model.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KycDocumentService {

    private final RestTemplate restTemplate;

    @Autowired
    public KycDocumentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String CUSTOMER_API_URL = "http://localhost:9090/customers/cif/";

    public String getCustomerIdByDocNumber(String documentNumber) {
        String url = CUSTOMER_API_URL + documentNumber;
        ParameterizedTypeReference<List<CustomerResponse>> typeReference = new ParameterizedTypeReference<List<CustomerResponse>>() {};
        ResponseEntity<List<CustomerResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);
        List<CustomerResponse> customers = response.getBody();
        if (customers != null && !customers.isEmpty()) {
            CustomerResponse customerResponse = customers.get(0);
            return customerResponse.getCustomerId();
        } else {
            return null;
        }
    }
}