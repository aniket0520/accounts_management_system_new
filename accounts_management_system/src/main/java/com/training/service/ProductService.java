package com.training.service;
import com.training.model.Product;

public interface ProductService {
    boolean isValidProduct(String accountType);

    Product getProduct(String accountType);

    boolean validateBalance(String accountType, double balance);
}