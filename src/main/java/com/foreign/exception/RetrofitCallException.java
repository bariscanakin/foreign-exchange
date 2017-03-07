package com.foreign.exception;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public class RetrofitCallException extends Exception {

    private int code;
    private String body;

    public RetrofitCallException(int code, String body) {
        super(code + " " + body);
        this.code = code;
        this.body = body;
    }
}
