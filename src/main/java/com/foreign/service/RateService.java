package com.foreign.service;

import com.foreign.http.client.RetrofitCallException;

import java.io.IOException;
import java.math.BigDecimal;

/**
* Created by bariscanakin on 7.3.2017.
 */
public interface RateService {

    BigDecimal getRate(String currencyFrom, String currencyTo) throws IOException, RetrofitCallException;
}
