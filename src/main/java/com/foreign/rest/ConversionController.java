package com.foreign.rest;

import com.foreign.rest.model.ConversionListRequest;
import com.foreign.rest.model.ConversionListResponse;
import com.foreign.rest.model.ConversionRequest;
import com.foreign.rest.model.ConversionResponse;
import com.foreign.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ConversionResponse doConversion(ConversionRequest request) {

        return null;
    }

    @RequestMapping(value = "/conversionList", method = RequestMethod.GET)
    public ConversionListResponse getConversionList(ConversionListRequest request) {

        return null;
    }

}
