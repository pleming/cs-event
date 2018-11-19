package com.event.cs.csevent.model;

public class ProductItem {
    private String csName;
    private byte[] productImage;
    private String productName;
    private String eventType;
    private String price;

    public ProductItem(String csName, byte[] productImage, String productName, String eventType, String price) {
        this.csName = csName;
        this.productImage = productImage;
        this.productName = productName;
        this.eventType = eventType;
        this.price = price;
    }

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
