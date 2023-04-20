package com.alphapay.api.model.beans;

public class PaymentCard {

    private String cardId;
    private BankCard info;

    private Boolean remember;

    //required when remember is true
    private String customerId;

    //required when remember is true
    private String email;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BankCard getInfo() {
        return info;
    }

    public void setInfo(BankCard info) {
        this.info = info;
    }

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
