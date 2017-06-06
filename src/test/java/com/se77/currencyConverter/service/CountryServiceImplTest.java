package com.se77.currencyConverter.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
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

        Assert.assertTrue(countries.size() >= 250 );

    }
}
