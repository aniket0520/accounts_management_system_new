package com.training.model;

public class ProductMasterResponse {
    private Long id;
    private Long productId;
    private String productName;
    private String productType;
    private Double interestRate;
    private Double minBalance;
    private Double accountOpenCharge;
    private String status;
    private String createdAt;
    private String updatedAt;

    public ProductMasterResponse() {
    }

    public ProductMasterResponse(Long id, Long productId, String productName, String productType, Double interestRate, Double minBalance, Double accountOpenCharge, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.interestRate = interestRate;
        this.minBalance = minBalance;
        this.accountOpenCharge = accountOpenCharge;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}