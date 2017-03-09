package com.foreign.rest.model;

import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public class ErrorResponse extends BaseRestResponse {

    private String message;

    private ErrorResponse(Builder builder) {
        super(false);
        this.message = builder.message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                "} " + super.toString();
    }

    public static class Builder {
        private String message;

        public Builder(String message) {
            this.message = message;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
