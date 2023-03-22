package com.alphapay.api.example;

import com.alphapay.api.AlphaPayClient;
import com.alphapay.api.DefaultAlphaPayClient;
import com.alphapay.api.base64.Base64Encryptor;
import com.alphapay.api.exception.AlphaPayApiException;
import com.alphapay.api.model.beans.Amount;
import com.alphapay.api.model.beans.Order;
import com.alphapay.api.model.beans.Refund;
import com.alphapay.api.model.beans.TransactionType;
import com.alphapay.api.request.beans.*;
import com.alphapay.api.response.beans.*;

public class OpenAPIExample {

    private static final String GATE_WAY_URL = "https://127.0.0.1:9433";
    private static final String merchantPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCoXmTiO1+tLpSG/ADqd0UuONKPdaZNtaC2JbHfhhvCv9Nv7ttnQoqyFa2BzXftQzDWrd2dN08FjKl40Qc/1i7f54ImULZ5qQsLZI7Hzy7r+rP2aZWFLq/CIcIHWsnZVQxoGn7VXdKu1gagTOdn6zdxqjqd7VQkBcK7sN9+tLzEI/6GdjmghMa2q9m6w69lnSr7wHlcSnGadf1oWnjPRZLLSlpYpyINW0nH2MEwCVXCz+V3dIzKsarvTXNIww117a4Hi4l+o9+5mBB1/5XwDVKrhnmMYA3FRyYuhqi9HMj5aoyWVv8bSi+w5K21S2Nw6gZLTKU7fS/pL44Leu6H0K6FAgMBAAECggEAAdYTthbtLnOXWZiGfwgheEVU4I0pLOhJ8iqbk8M/6sISUYJyoJ/saNBNNeDyiaMltXWseNI5Wadk0sU+1b6lgLoi5H2VW+LQ1MU6PGohSiAQFeQlZhAyCQU6uz9Ne8IOclU1pGiTS2ZYqKdmD69USbhQTtgfhowMiWT+rwBUHbR9PxRapWKiTPhXtu/ijLUN8pq7Y4kxY/OCs8WBsdYY6wFdHMmWtW6wzjM1+uWkRjMoiia1B9DW8xMKgY/k9Njzg4iQYWvhQfOyJwMvvcHKC4YLwOyHkOgkCOY+szNeHS/B3oQH3ZbehLiu5vpYQYwnU2+Na+taNcacUKAMwOUUSQKBgQC/CLcnaLRebeZlizSTXs+zGXbU3x9AjqClQxbW7VFgzVvYz/ljMH2Nmc6kR/q1qwSDJ5i7pALMddOo1o7Xm2r9/gUNq8U3aTfT7JPc14mu1y2WRz72sPwEZBXji2/7cC3zxHffnS7Cz5O3l7hCJ+BvyGPfCqoBuojRj8oFTgfL/QKBgQDhoHPdZGUowwi5H1D45KZfYdIEYb1ZpYXGt9vdkt+ZYDy5LLycYbFTolk0DySuTBxsUb18nY0yIyksovNJMpArKaSZvGQZpQJy51F8aBsgRSMnJ0zKRnPODgiv5w/99XMEtZy5uulVNWnPSUWunvC9m+9coYyqJtX7WLqJE+D/KQKBgBElPRsCYL3g6z7N4aggaE/Qx0OPywRGSNDMVq5vmlAv3kB1WhK29W3SEiPYef34HW2QDjs5Gy8ynA6ZQzTCtDWbqORzOD4i6WnR3+uARNE2atcECfimJdhXC0fi6egNR3KLfZ7KQrolW0KtEVKOtawStjP8hwmkrkbwutcDC9wZAoGAcblXTUsSNgaAOhcP0DQnq8H5gp3lO+9TS4NWvgsOyxcX5FopQ0V2hOo6viO34GaamPpmYQ5kodXZJheIOufShP77aUXsyoRChLoVd3hd29kdS9niOb5jFoQ6WrdDd0UlUUVdFrygaYN/rqyHA+o2+8tCTdVbulPS3VquwvMTIGkCgYEAvNFbJPv16JFsIftgnW6X1A4NAxIOMty+Pb8KqOxlgHUg5MQ9TxuANndxRCvAlWuWS9ivU6q1XOwTH0ku9O2HAGta9iT0ipR8WqG/EW/D5SljDwrDSP1YLidE0UWvQiY7lZdLAVbzbOpe//YJAuvngyVy35+63NXtvGr55AfGQFU=";
    private static final String alphaPayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtzXrrwBnjYnBr/I6Qd5yJ7N2EHNvlFY7aM/5lb2oljfWn3Ns/49Us9ytHtcabLu8rDqQBiouG+JjhVJT79sC0oKGxLyIpfmV4XvZjKb6cbx7NMqlZ9NaJc5UY4qCy75KOOtceRW9QwCsWYWgQtsC+3wKnbeIHTEWvYDoeX1Pi9mR2hI0zDOYKaQxnpTpd4LWok0IvPHYEHwAPwQn+x5swdIouFBx03CSKgmQC/u/Nac7zvw9rUHvPv7//uYLOJAi/1i3+uy5L8qsew+9IxhjUXvV5z1L78wth8Mr0KNjWhA6wlzCAB234PYOuLwJIycVMhtzkOJhMqcI8ZcfZia3uQIDAQAB";
    private static final String PARTNER_CODE = "CXVJIU";
    private static final AlphaPayClient defaultAlphaPayClient = new DefaultAlphaPayClient(GATE_WAY_URL, merchantPrivateKey, alphaPayPublicKey);

    private static Base64Encryptor base64Encryptor;
    public static CreateOrderResponse pay(String paymentRequestId) {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setPartnerCode(PARTNER_CODE);
        request.setPath("/api/v1/payments/pay");

        request.setProductCode("OFFLINE");
        request.setPaymentRequestId(paymentRequestId);
        Order order = new Order();
        Amount amount = new Amount();
        amount.setValue("100");
        amount.setCurrency("CAD");
        order.setOrderAmount(amount);
        order.setDescription("Test OFFLINE");
        order.setRedirectUrl("http://faqds.gw/khbw");
        order.setNotifyUrl("http://vdorirw.ua/vrn");
        request.setOrder(order);
        try {
            CreateOrderResponse response = defaultAlphaPayClient.execute(request);
            return response;
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static CreateRefundResponse refund(String refundRequestId) {
        CreateRefundRequest request = new CreateRefundRequest();
        request.setPartnerCode(PARTNER_CODE);
        request.setPath("/api/v1/payments/refund");

        Refund refund = new Refund();
        Amount amount = new Amount();
        amount.setValue("100");
        amount.setCurrency("CAD");
        refund.setRefundAmount(amount);
        refund.setDescription("Test Refund");
        refund.setNotifyUrl("http://vdorirw.ua/vrn");
        request.setRefund(refund);
        request.setRefundRequestId(refundRequestId);
        request.setPaymentId("1");
        try {
            CreateRefundResponse response = defaultAlphaPayClient.execute(request);
            return response;
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static CancelOrderResponse cancel(String paymentId) {
        CancelOrderRequest request = new CancelOrderRequest();
        request.setPartnerCode(PARTNER_CODE);
        request.setPath("/api/v1/payments/cancel");

        request.setPaymentId(paymentId);
        try {
            CancelOrderResponse response = defaultAlphaPayClient.execute(request);
            return response;
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static SearchOrderResponse searchOrder(String paymentId) {
        SearchOrderRequest request = new SearchOrderRequest();
        request.setPartnerCode(PARTNER_CODE);
        request.setPath("/api/v1/payments/inquiryPayment");

        request.setPaymentId(paymentId);
        try {
            SearchOrderResponse response = defaultAlphaPayClient.execute(request);
            return response;
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static SearchRefundResponse searchRefund(String refundId, String refundRequestId) {
        SearchRefundRequest request = new SearchRefundRequest();
        request.setPartnerCode(PARTNER_CODE);
        request.setPath("/api/v1/payments/inquiryRefund");

        request.setRefundId(refundId);
        request.setRefundRequestId(refundRequestId);
        try {
            SearchRefundResponse response = defaultAlphaPayClient.execute(request);
            return response;
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static SearchTransactionsResponse searchTransactions(String date) {
        SearchTransactionsRequest request = new SearchTransactionsRequest();
        request.setPartnerCode(PARTNER_CODE);
        request.setPath("/api/v1/payments/transactions");

        request.setDate(date);
        request.setPage(1);
        request.setPageLimit(10);
        request.setTransactionType(TransactionType.All);
        try {
            SearchTransactionsResponse response = defaultAlphaPayClient.execute(request);
            return response;
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static SearchSettlementsResponse searchSettlements(String date) {
        SearchSettlementsRequest request = new SearchSettlementsRequest();
        request.setPartnerCode(PARTNER_CODE);
        request.setPath("/api/v1/payments/settlements");

        request.setDate(date);
        try {
            SearchSettlementsResponse response = defaultAlphaPayClient.execute(request);
            return response;
        } catch (AlphaPayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SearchSettlementsResponse response = searchSettlements("20230317");
        System.out.println(response.getResult().getResultMessage());
    }
}
