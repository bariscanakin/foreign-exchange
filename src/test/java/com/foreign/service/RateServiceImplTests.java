package com.foreign.service;

import com.foreign.http.client.CurrencyServiceStrategy;
import com.foreign.service.impl.RateServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by bariscanakin on 10.3.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RateServiceImplTests {

    @MockBean
    private CurrencyServiceStrategy currencyService;

    @Autowired
    private RateServiceImpl rateService;

    @Test
    public void testGetRate() throws Exception {
        Mockito.when(this.currencyService.getLiveCurrency("USD", "TRY")).thenReturn(BigDecimal.valueOf(3.60d));
        BigDecimal rate = this.rateService.getRate("USD", "TRY");
        Assertions.assertThat(rate).isEqualTo(BigDecimal.valueOf(3.60d));
    }
}
