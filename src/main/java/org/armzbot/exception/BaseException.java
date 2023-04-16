package org.armzbot.exception;

import java.io.Serial;

public class BaseException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    private int errorCode;
    private String errorMessage;


    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, int errorCode) {


        super();
        this.errorCode = errorCode;
        this.errorMessage = msg;
        System.out.println("BaseException: " + msg);
        System.out.println("BaseException: " + errorCode);
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }


    @Override
    public String toString() {
        return this.errorCode + " : " + this.getErrorMessage();
    }
}
