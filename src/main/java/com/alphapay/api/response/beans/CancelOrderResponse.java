package com.alphapay.api.response.beans;

import com.alphapay.api.response.AlphaPayResponse;

import java.util.Date;

public class CancelOrderResponse extends AlphaPayResponse {

    //客户订单号
    private String paymentRequestId;
    //支付订单号
    private String paymentId;
    //撤销单创建时间
    private Date cancelTime;

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }
}
