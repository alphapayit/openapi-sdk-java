package com.alphapay.api.response.beans;

import com.alphapay.api.model.beans.Customer;
import com.alphapay.api.model.beans.Order;
import com.alphapay.api.model.beans.PaymentInfo;
import com.alphapay.api.model.beans.PaymentMethod;
import com.alphapay.api.response.AlphaPayResponse;

import java.util.Date;

public class CreateOrderResponse extends AlphaPayResponse {

    private Order order;
    private PaymentMethod paymentMethod;
    private Customer customer;
    private String paymentRequestId;
    private String productCode;

    private PaymentInfo paymentInfo;
    private Date paymentCreateTime;
    private Date paymentExpireTime;
    private String paymentId;

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

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
