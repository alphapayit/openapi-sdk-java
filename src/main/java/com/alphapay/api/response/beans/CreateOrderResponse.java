package com.alphapay.api.response.beans;

import com.alphapay.api.model.beans.*;
import com.alphapay.api.response.AlphaPayResponse;

import java.util.Date;

public class CreateOrderResponse extends AlphaPayResponse {

    private Ordered order;
    private PaymentMethod paymentMethod;
    private Customer customer;
    //Third Card
    private PaymentCard card;
    private String paymentRequestId;
    private String scenarioCode;

    private PaymentInfo paymentInfo;
    private Date paymentCreateTime;
    private Date paymentExpireTime;
    private String paymentId;

    public Ordered getOrder() {
        return order;
    }

    public void setOrder(Ordered order) {
        this.order = order;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PaymentCard getCard() {
        return card;
    }

    public void setCard(PaymentCard card) {
        this.card = card;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getScenarioCode() {
        return scenarioCode;
    }

    public void setScenarioCode(String scenarioCode) {
        this.scenarioCode = scenarioCode;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
