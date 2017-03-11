package com.foreign.domain.repository;

import com.foreign.domain.model.Conversion;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by bariscanakin on 10.3.2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ConversionRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ConversionRepository conversionRepository;

    @Test
    public void testInsertAndSelectById() {
        Conversion conversion = new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(4.0d),
                BigDecimal.valueOf(5.5d), BigDecimal.valueOf(22.0d)).build();
        Conversion savedConversion = this.entityManager.persist(conversion);
        Conversion foundConversion = this.conversionRepository.findOne(savedConversion.getId());
        Assertions.assertThat(foundConversion.getId()).isEqualTo(savedConversion.getId());
        Assertions.assertThat(foundConversion.getCurrencyFrom()).isEqualTo("USD");
        Assertions.assertThat(foundConversion.getCurrencyTo()).isEqualTo("TRY");
        Assertions.assertThat(foundConversion.getExchangeRate()).isEqualTo(BigDecimal.valueOf(4.0d));
        Assertions.assertThat(foundConversion.getAmountBefore()).isEqualTo(BigDecimal.valueOf(5.5d));
        Assertions.assertThat(foundConversion.getAmountAfter()).isEqualTo(BigDecimal.valueOf(22.0d));
    }

    @Test
    public void testInsertAndSelectByDate() {
        Conversion conversion1 = new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(4.0d),
                BigDecimal.valueOf(5.5d), BigDecimal.valueOf(22.0d)).build();
        Conversion conversion2 = new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(4.0d),
                BigDecimal.valueOf(6.25d), BigDecimal.valueOf(25.0d)).build();

        this.entityManager.persist(conversion1);
        this.entityManager.persist(conversion2);

        List<Conversion> conversionList = this.conversionRepository.findByConversionDate(new Date());
        Assertions.assertThat(conversionList.size()).isEqualTo(2);

        Conversion foundConversion1 = conversionList.get(0);
        Conversion foundConversion2 = conversionList.get(1);

        Assertions.assertThat(foundConversion1.getCurrencyFrom()).isEqualTo("USD");
        Assertions.assertThat(foundConversion1.getCurrencyTo()).isEqualTo("TRY");
        Assertions.assertThat(foundConversion1.getExchangeRate()).isEqualTo(BigDecimal.valueOf(4.0d));
        Assertions.assertThat(foundConversion1.getAmountBefore()).isEqualTo(BigDecimal.valueOf(5.5d));
        Assertions.assertThat(foundConversion1.getAmountAfter()).isEqualTo(BigDecimal.valueOf(22.0d));
        Assertions.assertThat(foundConversion2.getCurrencyFrom()).isEqualTo("USD");
        Assertions.assertThat(foundConversion2.getCurrencyTo()).isEqualTo("TRY");
        Assertions.assertThat(foundConversion2.getExchangeRate()).isEqualTo(BigDecimal.valueOf(4.0d));
        Assertions.assertThat(foundConversion2.getAmountBefore()).isEqualTo(BigDecimal.valueOf(6.25d));
        Assertions.assertThat(foundConversion2.getAmountAfter()).isEqualTo(BigDecimal.valueOf(25.0d));

    }
}
