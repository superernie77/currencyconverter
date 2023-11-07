package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.jpa.Conversion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

/**
 * Created by superernie77 on 05.06.2017.
 */
@SpringBootTest
public class ConversionServiceImplTest {

    @Autowired
    private ConverterService service;

    @Test
    public void testCurrencies(){
        List<String>  currencies = service.getCurrencies();

        // list with currencies has been created
        assertTrue(currencies.size() > 0);
    }

    @Test
    public void testConversion(){

        Conversion conversion = new Conversion();

        conversion.setSourceAmount(42d);
        conversion.setSourceCurrency("EUR");
        conversion.setTargetCurrency("GBP");
        conversion.setQueryDate(new Date());

        service.convert(conversion);

        // amount has been converted to target currency.
        assertTrue(conversion.getTargetAmount() > 0);
    }
}
