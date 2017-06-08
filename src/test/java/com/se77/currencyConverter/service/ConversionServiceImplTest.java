package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.jpa.Conversion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by superernie77 on 05.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConversionServiceImplTest {

    @Autowired
    private ConverterService service;

    @Test
    public void testConversion(){

        Conversion conversion = new Conversion();

        conversion.setSourceAmount(42d);
        conversion.setSourceCurrency("EUR");
        conversion.setTargetCurrency("GBP");
        conversion.setQueryDate(new Date());

        service.convert(conversion);

        // amount has been converted to target currency.
        Assert.assertTrue(conversion.getTargetAmount() > 0);
    }
}
