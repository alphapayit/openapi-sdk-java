package com.alphapay.api.response.beans;

import com.alphapay.api.model.beans.Settlement;
import com.alphapay.api.response.AlphaPayResponse;

import java.util.List;

public class SearchSettlementsResponse extends AlphaPayResponse {
    private List<Settlement> settlements;

    public List<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<Settlement> settlements) {
        this.settlements = settlements;
    }
}
