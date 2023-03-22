package com.alphapay.api.model.beans;

public class Ordered extends Order {

    //OFFLINE
    private Amount payAmount;

    public Amount getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Amount payAmount) {
        this.payAmount = payAmount;
    }
}
