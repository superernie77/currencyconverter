package com.se77.currencyConverter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

/**
 * Created by superernie77 on 05.06.2017.
 */
public class CurrencyLayerClientTest {

    private CurrencyLayerClient client;

    @BeforeEach
    public void setup(){
        client = new CurrencyLayerClient();

        client.setRestTemplate(new RestTemplate());
    }

    @Test
    public void testGetExchangeRate(){

        double rate = client.getExchangeAmount("EUR","GBP", 42d, new Date());

        assertTrue(rate > 0);
    }

    @Test
    public void testGetCurrencies(){

        List<String> currencies = client.getCurrencies();

        // all 169 supported currencies are listed
        assertTrue(currencies.size() > 0);
    }
}
