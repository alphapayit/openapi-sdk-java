package com.alphapay.api.response.beans;

import com.alphapay.api.model.beans.Ordered;
import com.alphapay.api.response.AlphaPayResponse;

import java.util.Date;

public class SearchOrderResponse extends AlphaPayResponse {

    private String paymentStatus;
    private String paymentRequestId;
    private String paymentId;
    private Ordered order;
    private Date paymentCreateTime;
    private Date paymentExpireTime;
    private Date paymentTime;
    private String scenarioCode;

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

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

    public Ordered getOrder() {
        return order;
    }

    public void setOrder(Ordered order) {
        this.order = order;
    }

    public Date getPaymentCreateTime() {
        return paymentCreateTime;
    }

    public void setPaymentCreateTime(Date paymentCreateTime) {
        this.paymentCreateTime = paymentCreateTime;
    }

    public Date getPaymentExpireTime() {
        return paymentExpireTime;
    }

    public void setPaymentExpireTime(Date paymentExpireTime) {
        this.paymentExpireTime = paymentExpireTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getScenarioCode() {
        return scenarioCode;
    }

    public void setScenarioCode(String scenarioCode) {
        this.scenarioCode = scenarioCode;
    }
}
