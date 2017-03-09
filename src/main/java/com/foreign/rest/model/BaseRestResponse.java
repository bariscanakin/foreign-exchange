package com.foreign.rest.model;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public abstract class BaseRestResponse {

    private boolean success;

    protected BaseRestResponse(boolean status) {
        this.success = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
