package com.alphapay.api;

import com.alphapay.api.exception.AlphaPayApiException;
import com.alphapay.api.request.AlphaPayRequest;
import com.alphapay.api.response.AlphaPayResponse;

public interface AlphaPayClient {

    <T extends AlphaPayResponse> T execute(AlphaPayRequest<T> alphaPayRequest) throws AlphaPayApiException;

}
