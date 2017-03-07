package com.foreign.rest.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public class RateRequest extends BaseRestRequest {

    @NotBlank
    @Length(min =  3, max = 3)
    private String currencyFrom;
    @NotBlank
    @Length(min =  3, max = 3)
    private String currencyTo;

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateRequest that = (RateRequest) o;
        return Objects.equals(currencyFrom, that.currencyFrom) &&
                Objects.equals(currencyTo, that.currencyTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyFrom, currencyTo);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RateRequest{");
        sb.append("currencyFrom='").append(currencyFrom).append('\'');
        sb.append(", currencyTo='").append(currencyTo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
