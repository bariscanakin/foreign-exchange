package com.foreign.rest.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public class ConversionRequest extends BaseRestRequest {

    private BigDecimal amount;
    private String currencyFrom;
    private String currencyTo;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

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
        ConversionRequest that = (ConversionRequest) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(currencyFrom, that.currencyFrom) &&
                Objects.equals(currencyTo, that.currencyTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currencyFrom, currencyTo);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ConversionRequest{");
        sb.append("amount=").append(amount);
        sb.append(", currencyFrom='").append(currencyFrom).append('\'');
        sb.append(", currencyTo='").append(currencyTo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
