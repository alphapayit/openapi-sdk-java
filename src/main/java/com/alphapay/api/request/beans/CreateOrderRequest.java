package com.alphapay.api.request.beans;

import com.alphapay.api.model.beans.*;
import com.alphapay.api.request.AlphaPayRequest;
import com.alphapay.api.response.beans.CreateOrderResponse;

public class CreateOrderRequest extends AlphaPayRequest<CreateOrderResponse> {

    //Order Id
    private String paymentRequestId;
    private String scenarioCode;

    private Order order;
    private PaymentMethod paymentMethod;
    private Customer customer;
    //Third Card
    private PaymentCard card;
    //APP
    private Env env;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    @Override
    public Class<CreateOrderResponse> getResponseClass() {
        return CreateOrderResponse.class;
    }
}
