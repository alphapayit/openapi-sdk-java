package com.alphapay.api.request.beans;

import com.alphapay.api.model.beans.TransactionType;
import com.alphapay.api.request.AlphaPayRequest;
import com.alphapay.api.response.beans.SearchTransactionsResponse;

public class SearchTransactionsRequest extends AlphaPayRequest<SearchTransactionsResponse> {

    // 账单流水日期（时区为UTC-8）, format: yyyyMMdd
    private String date;
    // 当前查询的页码
    private Integer page;
    // 当前查询页的条数10-100
    private Integer pageLimit = 10;
    // 账务类型：All（汇总）、Credit（付款）、Debit（退款）
    private TransactionType transactionType;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public Class<SearchTransactionsResponse> getResponseClass() {
        return SearchTransactionsResponse.class;
    }
}
