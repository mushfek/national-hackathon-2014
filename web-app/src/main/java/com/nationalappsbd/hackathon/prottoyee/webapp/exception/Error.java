package com.nationalappsbd.hackathon.prottoyee.webapp.exception;

/**
 * @author Abdullah Al Mamun Oronno
 */
public class Error {
    private Integer errorCode;
    private String errorMessage;

    public Error() {
    }

    public Error(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
