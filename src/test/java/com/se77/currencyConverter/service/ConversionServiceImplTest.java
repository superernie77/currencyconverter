package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.ConversionBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        ConversionBean conversionBean = new ConversionBean();

        conversionBean.setSourceAmount(42d);
        conversionBean.setSourceCurrency("EUR");
        conversionBean.setTargetCurrency("GBP");

        service.convert(conversionBean);

        Assert.assertTrue(conversionBean.getTargetAmount() > 0);
    }
}
