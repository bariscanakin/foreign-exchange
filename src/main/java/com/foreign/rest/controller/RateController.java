package com.foreign.rest.controller;

import com.foreign.http.client.RetrofitCallException;
import com.foreign.rest.model.RateRequest;
import com.foreign.rest.model.RateResponse;
import com.foreign.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public RateResponse getRate(@Valid RateRequest request) throws IOException, RetrofitCallException {
        BigDecimal rate = rateService.getRate(request.getCurrencyFrom(), request.getCurrencyTo());
        return new RateResponse.Builder(rate).build();
    }

}
