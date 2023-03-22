package com.alphapay.api.request.beans;

import com.alphapay.api.request.AlphaPayRequest;
import com.alphapay.api.response.beans.SearchSettlementsResponse;

public class SearchSettlementsRequest extends AlphaPayRequest<SearchSettlementsResponse> {

    //结算日（时区为UTC-8）, format: yyyyMMdd
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public Class<SearchSettlementsResponse> getResponseClass() {
        return SearchSettlementsResponse.class;
    }
}
