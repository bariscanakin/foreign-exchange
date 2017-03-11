package com.foreign.http.client;

import java.math.BigDecimal;

/**
 * Created by bariscanakin on 11.3.2017.
 */
public interface CurrencyServiceStrategy {

    BigDecimal getLiveCurrency(String currencyFrom, String currencyTo) throws Exception;
}
