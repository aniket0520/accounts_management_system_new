package com.training.model;

public class Product {
    private Integer productId;
    private String productName;
    private String productType;
    private Double interestRate;
    private Double minBalance;
    private Double accountOpenCharge;
    private String status;

    public Product(Integer productId, String productName, String productType,
                   Double interestRate, Double minBalance,
                   Double accountOpenCharge, String status) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.interestRate = interestRate;
        this.minBalance = minBalance;
        this.accountOpenCharge = accountOpenCharge;
        this.status = status;
    }

    // Getters & Setters

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(Double minBalance) {
        this.minBalance = minBalance;
    }

    public Double getAccountOpenCharge() {
        return accountOpenCharge;
    }

    public void setAccountOpenCharge(Double accountOpenCharge) {
        this.accountOpenCharge = accountOpenCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

