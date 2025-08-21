package com.training.service;

import com.training.model.Product;
import com.training.model.ProductMasterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private final WebClient productsWebClient;

    @Autowired
    public ProductServiceImpl(WebClient productsWebClient) {
        this.productsWebClient = productsWebClient;
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
        List<ProductMasterResponse> products = productsWebClient.get()
                .uri("search/name/" + productName)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductMasterResponse>>() {})
                .block();

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
