package com.alphapay.api;

import com.alphapay.api.exception.AlphaPayApiException;
import com.alphapay.api.net.DefaultHttpRPC;
import com.alphapay.api.net.HttpRpcResult;

import java.util.Map;

public class DefaultAlphaPayClient extends BaseAlphaPayClient {

    public DefaultAlphaPayClient(String gatewayUrl, String merchantPrivateKey, String alphaPayPublicKey ){
        super(gatewayUrl, merchantPrivateKey, alphaPayPublicKey);
    }

    @Override
    public Map<String, String> buildCustomHeader() {
        return null;
    }

    public HttpRpcResult sendRequest(String requestUrl, String httpMethod, Map<String, String> header, String reqBody)throws AlphaPayApiException {
        HttpRpcResult httpRpcResult;
        try {
            httpRpcResult = DefaultHttpRPC.doPost(requestUrl, header, reqBody);
        } catch (Exception e){
            throw new AlphaPayApiException(e);
        }
        return httpRpcResult;
    }

}
