package com.alphapay.api.response.beans;

import com.alphapay.api.model.beans.Pagination;
import com.alphapay.api.model.beans.Transaction;
import com.alphapay.api.response.AlphaPayResponse;

import java.util.List;

public class SearchTransactionsResponse extends AlphaPayResponse {
    private List<Transaction> transactions;
    private Pagination pagination;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
