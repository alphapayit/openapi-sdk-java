package com.alphapay.api;

import com.alibaba.fastjson.JSON;
import com.alphapay.api.exception.AlphaPayApiException;
import com.alphapay.api.model.Result;
import com.alphapay.api.net.HttpRpcResult;
import com.alphapay.api.request.AlphaPayRequest;
import com.alphapay.api.response.AlphaPayResponse;
import com.alphapay.api.tools.Constants;
import com.alphapay.api.tools.DateTool;
import com.alphapay.api.tools.SignatureTool;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class BaseAlphaPayClient implements AlphaPayClient {

    private static final Integer DEFULT_KEY_VERSION = 1;
    /**
     * eg:https://openapi.alphapay.com
     */
    private String gatewayUrl;
    /**
     * merchants private key
     */
    private String merchantPrivateKey;
    /**
     * alpha pay public key
     */
    private String alphaPayPublicKey;

    public BaseAlphaPayClient(){
    }

    public BaseAlphaPayClient(String gatewayUrl, String merchantPrivateKey, String alphaPayPublicKey ){
        this.gatewayUrl = gatewayUrl;
        this.merchantPrivateKey = merchantPrivateKey;
        this.alphaPayPublicKey = alphaPayPublicKey;
    }

    public <T extends AlphaPayResponse> T execute(AlphaPayRequest<T> alphaPayRequest) throws AlphaPayApiException {

        checkRequestParam(alphaPayRequest);

        String  merchantCode    = alphaPayRequest.getMerchantCode();
        String  httpMethod  = alphaPayRequest.getHttpMethod();
        String  path        = alphaPayRequest.getPath();
        Integer keyVersion  = alphaPayRequest.getKeyVersion();
        String  reqTime     = DateTool.getCurISO8601Time();
        String  nonce       = UUID.randomUUID().toString().replace("-", "");
        String  reqBody     = JSON.toJSONString(alphaPayRequest);

        /**
         * 对内容加签(Sign the content)
         */
        String signValue = genSignValue(httpMethod, path, merchantCode, reqTime, nonce, reqBody);

        /**
         *  生成必要header(Generate required headers)
         */
        Map<String, String> header       = buildBaseHeader(reqTime, nonce, merchantCode, keyVersion, signValue);
        Map<String, String> customHeader = buildCustomHeader();
        if(customHeader != null && customHeader.size() > 0){
            header.putAll(customHeader);
        }

        String requestUrl = genRequestUrl(path);
        /**
         * 向网关发起http请求(Make an HTTP request to the gateway)
         */
        HttpRpcResult rsp = sendRequest(requestUrl, httpMethod, header, reqBody);

        if(rsp == null){
            throw new AlphaPayApiException("HttpRpcResult is null.");
        }

        int    httpRespCode = rsp.getRspCode();
        String rspBody      = rsp.getRspBody();
        if(httpRespCode != Constants.HTTP_SUCCESS_CODE){
            throw new AlphaPayApiException("Response data error, rspBody:" + rspBody);
        }
        Class<T> responseClass   = alphaPayRequest.getResponseClass();
        T        alphaPayResponse  = JSON.parseObject(rspBody, responseClass);
        Result result          = alphaPayResponse.getResult();
        if(result == null){
            throw new AlphaPayApiException("Response data error, result field is null, rspBody:" + rspBody);
        }

        String rspSignValue = rsp.getRspSign();
        String rspTime      = rsp.getResponseTime();
        String rspNonce     = rsp.getRspNonce();
        if(null == rspSignValue || rspSignValue.isEmpty() || null == rspTime || rspTime.isEmpty() || null == rspNonce || rspNonce.isEmpty()){
            return alphaPayResponse;
        }

        /**
         * 对返回结果验签(Verify the result signature)
         */
        boolean isVerifySuccess = checkRspSign(httpMethod, path, merchantCode, rspTime, rspNonce, rspBody, rspSignValue);
        if(!isVerifySuccess){
            throw new AlphaPayApiException("Response signature verify fail.");
        }

        return alphaPayResponse;
    }

    private String genSignValue(String httpMethod, String path, String merchantCode, String requestTime, String nonce, String reqBody)throws AlphaPayApiException{
        String signatureValue;
        try{
            signatureValue = SignatureTool.sign(httpMethod, path, merchantCode, requestTime, nonce, reqBody, merchantPrivateKey);
        } catch(Exception e){
            throw new AlphaPayApiException(e);
        }
        return signatureValue;
    }

    private boolean checkRspSign(String httpMethod, String path, String merchantCode, String responseTime, String nonce, String rspBody, String rspSignValue) throws AlphaPayApiException{
        try{
            boolean isVerify = SignatureTool.verify(httpMethod, path, merchantCode, responseTime, nonce, rspBody, rspSignValue, alphaPayPublicKey);
            return isVerify;
        } catch(Exception e){
            throw new AlphaPayApiException(e);
        }

    }

    private void checkRequestParam(AlphaPayRequest alphaPayRequest) throws AlphaPayApiException{
        if(alphaPayRequest == null){
            throw new AlphaPayApiException("alphaPayRequest can't null");
        }

        String merchantCode = alphaPayRequest.getMerchantCode();
        String httpMethod = alphaPayRequest.getHttpMethod();
        String path = alphaPayRequest.getPath();

        if(StringUtils.isBlank(gatewayUrl)){
            throw new AlphaPayApiException("gatewayUrl can't null");
        }

        if(StringUtils.isBlank(merchantCode)){
            throw new AlphaPayApiException("merchantCode can't null");
        }

        if(StringUtils.isBlank(httpMethod)){
            throw new AlphaPayApiException("httpMethod can't null");
        }

        if(StringUtils.isBlank(path)){
            throw new AlphaPayApiException("path can't null");
        }

        if(!path.startsWith("/")){
            throw new AlphaPayApiException("path must start with /");
        }

    }

    private String genRequestUrl(String path){
        if(!gatewayUrl.startsWith("http://") && !gatewayUrl.startsWith("https://")){
            gatewayUrl = "https://" + gatewayUrl;
        }
        if(gatewayUrl.endsWith("/")){
            int len = gatewayUrl.length();
            gatewayUrl = gatewayUrl.substring(0, len - 1);
        }
        String requestUrl = gatewayUrl + path;
        return requestUrl;

    }

    private Map<String,String> buildBaseHeader(String requestTime, String nonce, String merchantCode, Integer keyVersion, String signatureValue){
        Map<String, String> header = new HashMap<String, String>();
        header.put(Constants.CONTENT_TYPE_HEADER, "application/json; charset=UTF-8");
        header.put(Constants.REQ_TIME_HEADER, requestTime);
        header.put(Constants.REQ_NONCE_HEADER, nonce);
        header.put(Constants.MERCHANT_CODE_HEADER, merchantCode);
        if(keyVersion == null){
            keyVersion = DEFULT_KEY_VERSION;
        }
        String signatureHeader = "algorithm=RS256,keyVersion=" + keyVersion + ",signature=" + signatureValue;
        header.put(Constants.REQ_SIGN_HEADER, signatureHeader);
        return header;
    }

    public abstract Map<String,String> buildCustomHeader();

    public abstract HttpRpcResult sendRequest(String requestUrl, String httpMethod, Map<String, String> header, String reqBody)throws AlphaPayApiException;


}
