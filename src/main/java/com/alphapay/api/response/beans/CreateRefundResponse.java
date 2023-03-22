package com.alphapay.api.response.beans;

import com.alphapay.api.model.beans.Refund;
import com.alphapay.api.response.AlphaPayResponse;

import java.util.Date;

public class CreateRefundResponse extends AlphaPayResponse {

    private Refund refund;
    //退款发起时间
    private Date refundTime;
    private String paymentId;
    private String refundRequestId;
    //平台退款订单号
    private String refundId;

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

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

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
}
