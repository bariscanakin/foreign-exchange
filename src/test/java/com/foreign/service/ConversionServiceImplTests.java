package com.foreign.service;

import com.foreign.domain.model.Conversion;
import com.foreign.domain.repository.ConversionRepository;
import com.foreign.service.impl.ConversionServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bariscanakin on 10.3.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConversionServiceImplTests {

    @MockBean
    private RateService rateService;

    @MockBean
    private ConversionRepository conversionRepository;

    @Autowired
    private ConversionServiceImpl conversionService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testSuccessfulConversion() throws Exception {
        Mockito.when(this.rateService.getRate("USD", "TRY")).thenReturn(BigDecimal.valueOf(3.50d));
        Conversion savedConversion = new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(3.50d), BigDecimal.valueOf(100d), BigDecimal.valueOf(350d)).build();
        Conversion returnedConversion = new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(3.50d), BigDecimal.valueOf(100d), BigDecimal.valueOf(350d)).id(1L).build();
        Mockito.when(this.conversionRepository.save(savedConversion)).thenReturn(returnedConversion);

        Conversion conversion = this.conversionService.exchangeCurrency("USD", "TRY", BigDecimal.valueOf(100d));
        Assertions.assertThat(conversion.getId()).isEqualTo(returnedConversion.getId());
        Assertions.assertThat(conversion.getCurrencyFrom()).isEqualTo(returnedConversion.getCurrencyFrom());
        Assertions.assertThat(conversion.getCurrencyTo()).isEqualTo(returnedConversion.getCurrencyTo());
        Assertions.assertThat(conversion.getExchangeRate()).isEqualTo(returnedConversion.getExchangeRate());
        Assertions.assertThat(conversion.getAmountBefore()).isEqualTo(returnedConversion.getAmountBefore());
        Assertions.assertThat(conversion.getAmountAfter()).isEqualTo(returnedConversion.getAmountAfter());
        Assertions.assertThat(conversion.getConversionDate()).isEqualTo(returnedConversion.getConversionDate());
    }

    @Test
    public void testSuccessfulgetListOfConversionsById() {
        Conversion conversion = new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(3.50d), BigDecimal.valueOf(100d), BigDecimal.valueOf(350d)).id(1L).build();
        Mockito.when(this.conversionRepository.findOne(1L)).thenReturn(conversion);
        List<Conversion> conversionList = this.conversionService.getListOfConversions(1L, null);
        Assertions.assertThat(conversionList).hasSize(1);
        Conversion returnedConversion = conversionList.get(0);
        Assertions.assertThat(returnedConversion).isEqualToComparingFieldByField(conversion);
    }

    @Test
    public void testSuccessfulgetListOfConversionsByConversionDate() {
        List<Conversion> conversionList = Arrays.asList(new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(3.75d),
                        BigDecimal.valueOf(100d), BigDecimal.valueOf(375d)).id(1L).build(),
                new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(3.75d),
                        BigDecimal.valueOf(100d), BigDecimal.valueOf(375d)).id(2L).build());
        Mockito.when(this.conversionRepository.findByConversionDate(LocalDate.now())).thenReturn(conversionList);
        List<Conversion> returnedConversionList = this.conversionService.getListOfConversions(null, LocalDate.now());
        Assertions.assertThat(returnedConversionList).hasSameSizeAs(conversionList);
        Assertions.assertThat(returnedConversionList).containsExactlyElementsOf(conversionList);
    }

    @Test
    public void testIllegalArgumentException() {
        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage("Only one of id and conversionDate can be null");
        this.conversionService.getListOfConversions(null, null);
    }
}
