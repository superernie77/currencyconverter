package com.se77.currencyConverter.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Tests the country service implementation.
 * Created by superernie77 on 06.06.2017.
 */
public class CountryServiceImplTest {

    private CountryServiceImpl countryService;

    @Before
    public void setup(){
        countryService = new CountryServiceImpl();
    }

    @Test
    public void testGetCountries(){

        List<String> countries = countryService.getCountries();

        // there should be around 250 countries in the list. exact number might change with different JVMs
        Assert.assertTrue(countries.size() > 0 );

    }
}
