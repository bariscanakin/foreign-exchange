package com.foreign.rest.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public class RateResponse extends BaseRestResponse {

    private BigDecimal exchangeRate;

    private RateResponse(Builder builder) {
        super(true);
        this.exchangeRate = builder.exchangeRate;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateResponse that = (RateResponse) o;
        return Objects.equals(exchangeRate, that.exchangeRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeRate);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RateResponse{");
        sb.append("exchangeRate=").append(exchangeRate);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private BigDecimal exchangeRate;

        public Builder(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public RateResponse build() {
            return new RateResponse(this);
        }
    }
}
