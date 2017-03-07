package com.foreign.rest;

import com.foreign.exception.RetrofitCallException;
import com.foreign.rest.model.RateRequest;
import com.foreign.rest.model.RateResponse;
import com.foreign.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@RestController
public class RateController {

    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public RateResponse getRate(RateRequest request) throws IOException, RetrofitCallException {
        BigDecimal rate = rateService.getRate(request.getCurrencyFrom(), request.getCurrencyTo());
        return new RateResponse.Builder(rate).build();
    }

}
