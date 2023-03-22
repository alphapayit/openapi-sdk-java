package com.alphapay.api.response.beans;

import com.alphapay.api.model.beans.Refund;
import com.alphapay.api.response.AlphaPayResponse;

import java.util.Date;

public class SearchRefundResponse extends AlphaPayResponse {

    private Refund refund;
    private String refundStatus;
    //退款发起时间
    private Date refundTime;
    //支付订单号
    private String paymentId;
    //客户提交的退款订单号
    private String refundRequestId;
    //平台退款订单号
    private String refundId;

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
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
