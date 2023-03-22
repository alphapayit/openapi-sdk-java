# OpenAPI SDK: A SDK for accessing AlphaPay services

OpenAPI SDK is a well encapsulated java utilities library which allow user access AlphaPay payment services directly and no need to write any other code for compile HTTPs request for remote services.

It includes a series of java APIs for developers to complete remote invoke between client and AlphaPay services and also provides signature tool for request signature generation and validation.

### Version 1.x

First version of SDK APIs. Include payment, refund, cancel and search APIs.

## Getting started

### Requirements
- Java 8+

### Setting up the dependency

The first step is to include OpenAPI SDK into your project, for example, as a Gradle compile dependency:

```groovy
implementation "com.alphapay.sdk:openapi-sdk-java:1.0.0"
```

and for Maven:

```xml
<dependency>
    <groupId>com.alphapay.sdk</groupId>
    <artifactId>openapi-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Start from creating a payment order

The second is to write a method to access remote services:

```java
package com.alphapay.api.example;

import com.alphapay.api.*;

public class OpenAPIExample {

        CreateOrderRequest request = new CreateOrderRequest();
        request.setMerchantCode(MERCHANT_CODE);
        request.setPath(APIs.PAY);

        request.setScenarioCode("OFFLINE_QRCODE");
        request.setPaymentRequestId(paymentRequestId);
        Order order = new Order();
        Amount amount = new Amount();
        amount.setValue("100");
        amount.setCurrency("CAD");
        order.setOrderAmount(amount);
        order.setDescription("Test OFFLINE_QRCODE");
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
}
```

### Base classes

 - `com.alphapay.api.DefaultAlphaPayClient`: A client for communicating with AlphaPay services.
 - `com.alphapay.api.request.AlphaPayRequest`: All requests for AlphaPay services are based on AlphaPayRequest.
 - `com.alphapay.api.response.AlphaPayResponse`: The response from AlphaPay services will be compiled as an AlphaPayResponse.
 - `com.alphapay.api.exception.AlphaPayApiException`: The exception raised during the communication with remote service must be handled.

### Initial an AlphaPay client

The gateway url is our Open API domain: `https://openapi.alphapay.com`. How to get the merchant private key and AlphaPay public key can be found in our [API reference documents](https://www.alphapay.com/api/CAD_en.html).

```java
private static final String GATE_WAY_URL = "https://openapi.alphapay.com";
private static final String merchantPrivateKey = "MIIEvQIBA";
private static final String alphaPayPublicKey = "MIIBIjANBq";

AlphaPayClient defaultAlphaPayClient = new DefaultAlphaPayClient(GATE_WAY_URL, merchantPrivateKey, alphaPayPublicKey);
```

### Complete a remote invoke

The `AlphaPayResponse` is the base class of all service response type. You can just use the subclass of `AlphaPayResponse`, like: `CreateOrderResponse`.

```java
try {
    AlphaPayResponse response = defaultAlphaPayClient.execute(request);
    return response;
} catch (AlphaPayApiException e) {
    // handle exception at here
}
```

### About APIs Document

To check out our HTTP API documents for the details of remote invoke between client and AlphaPay services, please visit: [API reference documents](https://www.alphapay.com/api/CAD_en.html).

## Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues](https://github.com/alphapayit/openapi-sdk-java/issues).

## LICENSE

Copyright (c) 2023-present, AlphaPay

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
