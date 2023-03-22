package com.alphapay.api.request.beans;

import com.alphapay.api.request.AlphaPayRequest;
import com.alphapay.api.response.beans.SearchOrderResponse;

public class SearchOrderRequest extends AlphaPayRequest<SearchOrderResponse> {

    private String paymentId;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public Class<SearchOrderResponse> getResponseClass() {
        return SearchOrderResponse.class;
    }
}
