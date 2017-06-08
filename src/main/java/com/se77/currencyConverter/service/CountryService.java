package com.se77.currencyConverter.service;

import java.util.List;

/**
 * Creates a list of supported countries for user registration
 * Created by superernie77 on 06.06.2017.
 */
public interface CountryService {

    /**
     * @return a list of all countries.
     */
    public List<String> getCountries();
}
