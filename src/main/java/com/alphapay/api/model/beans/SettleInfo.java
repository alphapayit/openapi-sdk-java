package com.alphapay.api.model.beans;

public class SettleInfo {

     //清算唯一编号
     private String settleNo;
     //本次结算Credit数量
     private String creditNum;
     //本次结算Debit数量
     private String debitNum;
     //本次结算Credit金额
     private String creditAmount;
     //本次结算Debit金额
     private String debitAmount;
     //本次结算净交易金额（creditAmount-debitAmount）
     private String netAmount;
     //平台服务费
     private String serviceFee;
     //商户结算金额
     private String settleAmount;

     public String getSettleNo() {
          return settleNo;
     }

     public void setSettleNo(String settleNo) {
          this.settleNo = settleNo;
     }

     public String getCreditNum() {
          return creditNum;
     }

     public void setCreditNum(String creditNum) {
          this.creditNum = creditNum;
     }

     public String getDebitNum() {
          return debitNum;
     }

     public void setDebitNum(String debitNum) {
          this.debitNum = debitNum;
     }

     public String getCreditAmount() {
          return creditAmount;
     }

     public void setCreditAmount(String creditAmount) {
          this.creditAmount = creditAmount;
     }

     public String getDebitAmount() {
          return debitAmount;
     }

     public void setDebitAmount(String debitAmount) {
          this.debitAmount = debitAmount;
     }

     public String getNetAmount() {
          return netAmount;
     }

     public void setNetAmount(String netAmount) {
          this.netAmount = netAmount;
     }

     public String getServiceFee() {
          return serviceFee;
     }

     public void setServiceFee(String serviceFee) {
          this.serviceFee = serviceFee;
     }

     public String getSettleAmount() {
          return settleAmount;
     }

     public void setSettleAmount(String settleAmount) {
          this.settleAmount = settleAmount;
     }
}
