package com.foreign.service.impl;

import com.foreign.http.client.CurrencyServiceStrategy;
import com.foreign.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@Service
public class RateServiceImpl implements RateService {

    private CurrencyServiceStrategy currencyService;

    @Autowired
    public RateServiceImpl(CurrencyServiceStrategy currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public BigDecimal getRate(String currencyFrom, String currencyTo) throws Exception {
        return currencyService.getLiveCurrency(currencyFrom, currencyTo);

    }
}
