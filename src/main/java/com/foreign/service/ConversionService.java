package com.foreign.service;

import com.foreign.domain.model.Conversion;
import com.foreign.http.client.RetrofitCallException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by bariscanakin on 7.3.2017.
 */
public interface ConversionService {

    Conversion exchangeCurrency(String currencyFrom, String currencyTo, BigDecimal amount) throws Exception;

    List<Conversion> getListOfConversions(Long id, Date conversionDate);
}
