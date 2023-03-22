package com.alphapay.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.alphapay.api.net.HttpMethod;
import com.alphapay.api.response.AlphaPayResponse;

public abstract class AlphaPayRequest<T extends AlphaPayResponse>{

    @JSONField(serialize = false)
    private String merchantCode;
    @JSONField(serialize = false)
    private String path;
    @JSONField(serialize = false)
    private Integer keyVersion;
    @JSONField(serialize = false)
    private  Class<T> responseClass;
    @JSONField(serialize = false)
    private static String httpMethod = HttpMethod.POST.name();

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(Integer keyVersion) {
        this.keyVersion = keyVersion;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    public abstract Class<T> getResponseClass();

}
