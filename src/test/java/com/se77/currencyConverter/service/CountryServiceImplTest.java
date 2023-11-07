package com.se77.currencyConverter.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the country service implementation.
 * Created by superernie77 on 06.06.2017.
 */
public class CountryServiceImplTest {

    private CountryServiceImpl countryService;

    @BeforeEach
    public void setup(){
        countryService = new CountryServiceImpl();
    }

    @Test
    public void testGetCountries(){

        List<String> countries = countryService.getCountries();

        // there should be around 250 countries in the list. exact number might change with different JVMs
        assertTrue(countries.size() > 0 );

    }
}
