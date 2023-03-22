package com.alphapay.api.request.beans;

import com.alphapay.api.request.AlphaPayRequest;
import com.alphapay.api.response.beans.CancelOrderResponse;

public class CancelOrderRequest extends AlphaPayRequest<CancelOrderResponse> {

    private String paymentId;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public Class<CancelOrderResponse> getResponseClass() {
        return CancelOrderResponse.class;
    }
}
