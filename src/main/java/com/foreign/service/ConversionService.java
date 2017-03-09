package com.foreign.service;

import com.foreign.domain.Conversion;
import com.foreign.exception.RetrofitCallException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public interface ConversionService {

    Conversion exchangeCurrency(String currencyFrom, String currencyTo, BigDecimal amount) throws IOException, RetrofitCallException;

    List<Conversion> getListOfConversions(Long id, Date conversionDate);
}
