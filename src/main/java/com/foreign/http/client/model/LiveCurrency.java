package com.foreign.http.client.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public class LiveCurrency {

    private boolean success;
    private String source;
    private Map<String, BigDecimal> quotes;
    private String terms;
    private String privacy;
    private Long timestamp;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map<String, BigDecimal> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, BigDecimal> quotes) {
        this.quotes = quotes;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiveCurrency that = (LiveCurrency) o;
        return success == that.success &&
                Objects.equals(source, that.source) &&
                Objects.equals(quotes, that.quotes) &&
                Objects.equals(terms, that.terms) &&
                Objects.equals(privacy, that.privacy) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, source, quotes, terms, privacy, timestamp);
    }

    @Override
    public String toString() {
        return "LiveCurrency{" +
                "success=" + success +
                ", source='" + source + '\'' +
                ", quotes=" + quotes +
                ", terms='" + terms + '\'' +
                ", privacy='" + privacy + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
