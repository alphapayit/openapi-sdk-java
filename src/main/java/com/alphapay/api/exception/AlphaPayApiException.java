package com.alphapay.api.exception;

public class AlphaPayApiException extends Exception {

    private static final long serialVersionUID = 6341441272448887609L;

    private String errCode;
    private String errMsg;

    public AlphaPayApiException() {
        super();
    }

    public AlphaPayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlphaPayApiException(String message) {
        super(message);
    }

    public AlphaPayApiException(Throwable cause) {
        super(cause);
    }

    public AlphaPayApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

}
