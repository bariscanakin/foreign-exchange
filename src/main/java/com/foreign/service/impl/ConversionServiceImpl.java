package com.foreign.service.impl;

import com.foreign.domain.model.Conversion;
import com.foreign.domain.repository.ConversionRepository;
import com.foreign.service.ConversionService;
import com.foreign.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@Service
public class ConversionServiceImpl implements ConversionService {

    private RateService rateService;

    private ConversionRepository conversionRepository;

    @Autowired
    public ConversionServiceImpl(RateService rateService, ConversionRepository conversionRepository) {
        this.rateService = rateService;
        this.conversionRepository = conversionRepository;
    }

    @Override
    public Conversion exchangeCurrency(String currencyFrom, String currencyTo, BigDecimal amount) throws Exception {
        BigDecimal rate = rateService.getRate(currencyFrom, currencyTo);

        BigDecimal exchangedAmount = amount.multiply(rate);

        Conversion conversion = new Conversion.Builder(currencyFrom, currencyTo, rate, amount, exchangedAmount).build();

        return conversionRepository.save(conversion);
    }

    @Override
    public List<Conversion> getListOfConversions(Long id, Date conversionDate) {
        if (id != null) {
            return getListOfConversionsById(id);
        }

        if (conversionDate != null) {
            return getListOfConversionsByConversionDate(conversionDate);
        }

        throw new IllegalArgumentException("Only one of id and conversionDate can be null");
    }

    private List<Conversion> getListOfConversionsByConversionDate(Date conversionDate) {
        return conversionRepository.findByConversionDate(conversionDate);
    }

    private List<Conversion> getListOfConversionsById(Long id) {
        List<Conversion> conversionList = new ArrayList<>();
        if (id != null) {
            Conversion conversion = conversionRepository.findOne(id);
            conversionList.add(conversion);
        }
        return conversionList;
    }
}
