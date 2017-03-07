package com.foreign.rest.model;

import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public class ConversionResponse extends BaseRestResponse {

    private Long id;
    private Long amount;

    public ConversionResponse(boolean status, Long id) {
        super(status);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversionResponse that = (ConversionResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount);
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
}
