package com.foreign.rest.controller;

import com.foreign.domain.Conversion;
import com.foreign.exception.RetrofitCallException;
import com.foreign.rest.model.ConversionListRequest;
import com.foreign.rest.model.ConversionListResponse;
import com.foreign.rest.model.ConversionRequest;
import com.foreign.rest.model.ConversionResponse;
import com.foreign.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@RestController
public class ConversionController {

    private ConversionService conversionService;

    @Autowired
    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @RequestMapping(value = "/conversion", method = RequestMethod.POST)
    public ConversionResponse doConversion(@RequestBody @Valid ConversionRequest request) throws IOException, RetrofitCallException {
        Conversion conversion = conversionService.exchangeCurrency(request.getCurrencyFrom(), request.getCurrencyTo(), request.getAmount());

        return new ConversionResponse.Builder(conversion.getId(), conversion.getAmountAfter()).build();
    }

    @RequestMapping(value = "/conversionList", method = RequestMethod.GET)
    public ConversionListResponse getConversionList(@Valid ConversionListRequest request) {
        List<Conversion> conversionList = conversionService.getListOfConversions(request.getId(), request.getDate());

        return new ConversionListResponse.Builder(conversionList).build();
    }

}
