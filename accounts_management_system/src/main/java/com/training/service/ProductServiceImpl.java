package com.training.service;

import com.training.model.Product;
import com.training.model.ProductMasterResponse;
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
public class ProductServiceImpl implements ProductService {
    private final RestTemplate restTemplate;

    @Autowired
    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String PRODUCT_API_URL = "http://localhost:5050/products/search/name/";

    @Override
    public boolean isValidProduct(String productName) {
        Product product = getProduct(productName);
        return product != null;
    }

    @Override
    public Product getProduct(String productName) {
        String url = PRODUCT_API_URL + productName;
        ParameterizedTypeReference<List<ProductMasterResponse>> typeReference = new ParameterizedTypeReference<List<ProductMasterResponse>>() {};
        ResponseEntity<List<ProductMasterResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, typeReference);
        List<ProductMasterResponse> products = response.getBody();
        if (products != null && !products.isEmpty()) {
            ProductMasterResponse productMasterResponse = products.get(0);
            return mapToProduct(productMasterResponse);
        } else {
            return null;
        }
    }

    private Product mapToProduct(ProductMasterResponse productMasterResponse) {
        return new Product(
                productMasterResponse.getProductId().intValue(),
                productMasterResponse.getProductName(),
                productMasterResponse.getProductType(),
                productMasterResponse.getInterestRate(),
                productMasterResponse.getMinBalance(),
                productMasterResponse.getAccountOpenCharge(),
                productMasterResponse.getStatus()
        );
    }

    @Override
    public boolean validateBalance(String productName, double balance) {
        Product product = getProduct(productName);
        if (product == null) {
            return false;
        } else {
            return balance >= product.getMinBalance();
        }
    }
}
