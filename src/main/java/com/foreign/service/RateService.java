package com.foreign.service;

import java.math.BigDecimal;

/**
* Created by bariscanakin on 7.3.2017.
 */
public interface RateService {

    BigDecimal getRate(String currencyFrom, String currencyTo) throws Exception;
}
