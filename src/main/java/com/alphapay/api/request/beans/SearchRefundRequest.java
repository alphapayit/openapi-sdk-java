package com.alphapay.api.request.beans;

import com.alphapay.api.request.AlphaPayRequest;
import com.alphapay.api.response.beans.SearchRefundResponse;

public class SearchRefundRequest extends AlphaPayRequest<SearchRefundResponse> {

    private String refundId;
    private String refundRequestId;

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(String refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    @Override
    public Class<SearchRefundResponse> getResponseClass() {
        return SearchRefundResponse.class;
    }
}
