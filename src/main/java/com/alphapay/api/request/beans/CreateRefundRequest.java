package com.alphapay.api.request.beans;

import com.alphapay.api.model.beans.Refund;
import com.alphapay.api.request.AlphaPayRequest;
import com.alphapay.api.response.beans.CreateRefundResponse;

public class CreateRefundRequest extends AlphaPayRequest<CreateRefundResponse> {

    //支付订单号
    private String paymentId;
    //客户提交的退款订单号
    private String refundRequestId;
    //退款实体
    private Refund refund;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(String refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    @Override
    public Class<CreateRefundResponse> getResponseClass() {
        return CreateRefundResponse.class;
    }
}
