package com.alphapay.api.model.beans;

public class Order {

    private Amount orderAmount;

    private String description;
    private String notifyUrl;
    private String redirectUrl;

    public Amount getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Amount orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    
}
