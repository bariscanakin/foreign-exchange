package com.foreign.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@Entity
public class Conversion {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String currencyFrom;
    @Column
    private String currencyTo;
    @Column
    private BigDecimal exchangeRate;
    @Column
    private BigDecimal amountBefore;
    @Column
    private BigDecimal amountAfter;
    @Column
    private LocalDate conversionDate;

    // this exists because of JPA :(
    public Conversion() {
    }

    private Conversion(Builder builder) {
        this.id = builder.id;
        this.currencyFrom = builder.currencyFrom;
        this.currencyTo = builder.currencyTo;
        this.exchangeRate = builder.exchangeRate;
        this.amountBefore = builder.amountBefore;
        this.amountAfter = builder.amountAfter;
        this.conversionDate = builder.conversionDate;
    }

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

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
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

    public LocalDate getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(LocalDate conversionDate) {
        this.conversionDate = conversionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversion that = (Conversion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(currencyFrom, that.currencyFrom) &&
                Objects.equals(currencyTo, that.currencyTo) &&
                Objects.compare(exchangeRate, that.exchangeRate, BigDecimal::compareTo) == 0 &&
                Objects.compare(amountBefore, that.amountBefore, BigDecimal::compareTo) == 0 &&
                Objects.compare(amountAfter, that.amountAfter, BigDecimal::compareTo) == 0 &&
                Objects.equals(conversionDate, that.conversionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyFrom, currencyTo, exchangeRate, amountBefore, amountAfter, conversionDate);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Conversion{");
        sb.append("id=").append(id);
        sb.append(", currencyFrom='").append(currencyFrom).append('\'');
        sb.append(", currencyTo='").append(currencyTo).append('\'');
        sb.append(", exchangeRate=").append(exchangeRate);
        sb.append(", amountBefore=").append(amountBefore);
        sb.append(", amountAfter=").append(amountAfter);
        sb.append(", conversionDate=").append(conversionDate);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private Long id;
        private String currencyFrom;
        private String currencyTo;
        private BigDecimal exchangeRate;
        private BigDecimal amountBefore;
        private BigDecimal amountAfter;
        private LocalDate conversionDate;

        public Builder(String currencyFrom, String currencyTo, BigDecimal exchangeRate, BigDecimal amountBefore, BigDecimal amountAfter) {
            this.currencyFrom = currencyFrom;
            this.currencyTo = currencyTo;
            this.exchangeRate = exchangeRate;
            this.amountBefore = amountBefore;
            this.amountAfter = amountAfter;
            this.conversionDate = LocalDate.now();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder conversionDate(LocalDate conversionDate) {
            this.conversionDate = conversionDate;
            return this;
        }

        public Conversion build() {
            return new Conversion(this);
        }
    }
}
