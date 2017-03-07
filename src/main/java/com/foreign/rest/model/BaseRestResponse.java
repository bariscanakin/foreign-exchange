package com.foreign.rest.model;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public abstract class BaseRestResponse {

    private boolean status;

    public BaseRestResponse(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
