package com.se77.currencyConverter.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by superernie77 on 05.06.2017.
 */
public class CurrencyLayerClientTest {

    private CurrencyLayerClient client;

    @Before
    public void setup(){
        client = new CurrencyLayerClient();
    }

    @Test
    public void testGetExchangeRate(){

        double rate = client.getExchangeRate("EUR","GBP", 42d);

        Assert.assertTrue(rate > 0);
    }

    @Test
    public void testGetCurrencies(){

        List<String> currencies = client.getCurrencies();

        // all 169 supported currencies are listed
        Assert.assertTrue(currencies.size() == 169);
    }
}
