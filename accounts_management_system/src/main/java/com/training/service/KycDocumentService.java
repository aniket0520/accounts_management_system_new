package com.training.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KycDocumentService {

    private final Map<String, Long> documentToCustomerMap = new HashMap<>();

    public KycDocumentService() {
        // Test data
        documentToCustomerMap.put("DOC123", 101L);
        documentToCustomerMap.put("DOC456", 102L);
        documentToCustomerMap.put("DOC789", 103L);
    }

    public Long getCustomerIdByDocNumber(String documentNumber) {
        return documentToCustomerMap.get(documentNumber);
    }
}
