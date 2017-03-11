package com.foreign.rest.model;

import com.foreign.rest.validator.ValidConversionListRequest;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@ValidConversionListRequest
public class ConversionListRequest extends BaseRestRequest {

    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    public ConversionListRequest() {
    }

    public ConversionListRequest(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversionListRequest that = (ConversionListRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ConversionListRequest{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
