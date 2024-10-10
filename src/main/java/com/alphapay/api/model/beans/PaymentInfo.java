package com.alphapay.api.model.beans;

public class PaymentInfo {

    //OFFLINE_QRCODE, ONLINE_QRCODE
    private String paymentUrl;
    private String paymentQRImage;

    //NATIVE_JSAPI
    private Object sdkParams;

    //JSAPI, ONLINE_WAP, ONLINE_WEB, ONLINE_QRCODE
    private String payUrl;

    //ONLINE_WAP
    private String channelPayUrl;

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

    public Object getSdkParams() {
        return sdkParams;
    }

    public void setSdkParams(Object sdkParams) {
        this.sdkParams = sdkParams;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getChannelPayUrl() {
        return channelPayUrl;
    }

    public void setChannelPayUrl(String channelPayUrl) {
        this.channelPayUrl = channelPayUrl;
    }
}
