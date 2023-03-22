package com.alphapay.api.model.beans;

import java.util.Map;

public class PaymentInfo {

    //OFFLINE, ONLINE_QRCODE
    private String paymentUrl;
    private String paymentQRImage;

    //NATIVE_JSAPI
    private Map<String, ?> sdkParams;

    //JSAPI, ONLINE_WAP, ONLINE_WEB, ONLINE_QRCODE
    private String payUrl;

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getPaymentQRImage() {
        return paymentQRImage;
    }

    public void setPaymentQRImage(String paymentQRImage) {
        this.paymentQRImage = paymentQRImage;
    }

    public Map<String, ?> getSdkParams() {
        return sdkParams;
    }

    public void setSdkParams(Map<String, ?> sdkParams) {
        this.sdkParams = sdkParams;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}
