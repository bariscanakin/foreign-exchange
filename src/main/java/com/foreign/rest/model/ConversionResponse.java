package com.foreign.rest.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public class ConversionResponse extends BaseRestResponse {

    private Long id;
    private BigDecimal amount;

    private ConversionResponse(Builder builder) {
        super(true);
        this.id = builder.id;
        this.amount = builder.amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversionResponse that = (ConversionResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.compare(amount, that.amount, BigDecimal::compareTo) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ConversionResponse{");
        sb.append("id=").append(id);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private Long id;
        private BigDecimal amount;

        public Builder(Long id, BigDecimal amount) {
            this.id = id;
            this.amount = amount;
        }

        public ConversionResponse build() {
            return new ConversionResponse(this);
        }
    }
}
