package com.alphapay.api.model.beans;

public class Settlement {
    
    private SettleInfo settleInfo;
    private SettleBank bankInfo;

    public SettleInfo getSettleInfo() {
        return settleInfo;
    }

    public void setSettleInfo(SettleInfo settleInfo) {
        this.settleInfo = settleInfo;
    }

    public SettleBank getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(SettleBank bankInfo) {
        this.bankInfo = bankInfo;
    }
}
