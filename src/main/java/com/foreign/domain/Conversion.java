package com.foreign.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@Entity
public class Conversion {

    @Id
    @GeneratedValue
    private Long id;

    private String currencyFrom;

    private String currenyTo;

    private BigDecimal exchangeRate;

    private BigDecimal amountBefore;

    private BigDecimal amountAfter;

    private Date conversionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrenyTo() {
        return currenyTo;
    }

    public void setCurrenyTo(String currenyTo) {
        this.currenyTo = currenyTo;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getAmountBefore() {
        return amountBefore;
    }

    public void setAmountBefore(BigDecimal amountBefore) {
        this.amountBefore = amountBefore;
    }

    public BigDecimal getAmountAfter() {
        return amountAfter;
    }

    public void setAmountAfter(BigDecimal amountAfter) {
        this.amountAfter = amountAfter;
    }

    public Date getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(Date conversionDate) {
        this.conversionDate = conversionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversion that = (Conversion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(currencyFrom, that.currencyFrom) &&
                Objects.equals(currenyTo, that.currenyTo) &&
                Objects.equals(exchangeRate, that.exchangeRate) &&
                Objects.equals(amountBefore, that.amountBefore) &&
                Objects.equals(amountAfter, that.amountAfter) &&
                Objects.equals(conversionDate, that.conversionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyFrom, currenyTo, exchangeRate, amountBefore, amountAfter, conversionDate);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Conversion{");
        sb.append("id=").append(id);
        sb.append(", currencyFrom='").append(currencyFrom).append('\'');
        sb.append(", currenyTo='").append(currenyTo).append('\'');
        sb.append(", exchangeRate=").append(exchangeRate);
        sb.append(", amountBefore=").append(amountBefore);
        sb.append(", amountAfter=").append(amountAfter);
        sb.append(", conversionDate=").append(conversionDate);
        sb.append('}');
        return sb.toString();
    }
}
