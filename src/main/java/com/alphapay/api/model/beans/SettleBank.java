package com.alphapay.api.model.beans;

public class SettleBank {

     //店铺编号：商家自行设置
     private String storeCode;
     //商户银行开户名称
     private String companyName;
     private String transitNo;
     private String institutionNo;
     private String accountNo;

     public String getStoreCode() {
          return storeCode;
     }

     public void setStoreCode(String storeCode) {
          this.storeCode = storeCode;
     }

     public String getCompanyName() {
          return companyName;
     }

     public void setCompanyName(String companyName) {
          this.companyName = companyName;
     }

     public String getTransitNo() {
          return transitNo;
     }

     public void setTransitNo(String transitNo) {
          this.transitNo = transitNo;
     }

     public String getInstitutionNo() {
          return institutionNo;
     }

     public void setInstitutionNo(String institutionNo) {
          this.institutionNo = institutionNo;
     }

     public String getAccountNo() {
          return accountNo;
     }

     public void setAccountNo(String accountNo) {
          this.accountNo = accountNo;
     }
}
