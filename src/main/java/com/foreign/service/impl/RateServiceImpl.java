package com.foreign.service.impl;

import com.foreign.http.client.CurrencyLayerClient;
import com.foreign.exception.RetrofitCallException;
import com.foreign.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@Service
public class RateServiceImpl implements RateService {

    private CurrencyLayerClient client;

    @Autowired
    public RateServiceImpl(CurrencyLayerClient client) {
        this.client = client;
    }

    @Override
    public BigDecimal getRate(String currencyFrom, String currencyTo) throws IOException, RetrofitCallException {
        return client.getLiveCurrency(currencyFrom, currencyTo);

    }
}
