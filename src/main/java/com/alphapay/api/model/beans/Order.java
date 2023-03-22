package com.alphapay.api.model.beans;

public class Order {

    private Amount orderAmount;
    //OFFLINE_QROCDE
    private Amount payAmount;
    private String description;
    private String notifyUrl;
    private String redirectUrl;
    //APP
    private Env env;

    public Amount getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Amount orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Amount getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Amount payAmount) {
        this.payAmount = payAmount;
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

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }
}
