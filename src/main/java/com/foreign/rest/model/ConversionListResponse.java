package com.foreign.rest.model;

import com.foreign.domain.Conversion;

import java.util.List;
import java.util.Objects;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public class ConversionListResponse extends BaseRestResponse {

    private List<Conversion> conversionList;

    public ConversionListResponse(boolean status, List<Conversion> conversionList) {
        super(status);
        this.conversionList = conversionList;
    }

    public List<Conversion> getConversionList() {
        return conversionList;
    }

    public void setConversionList(List<Conversion> conversionList) {
        this.conversionList = conversionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversionListResponse that = (ConversionListResponse) o;
        return Objects.equals(conversionList, that.conversionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversionList);
    }

    @Override
    public String toString() {
        return "ConversionListResponse{" +
                "conversionList=" + conversionList +
                "} " + super.toString();
    }
}