package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.jpa.Conversion;

import java.util.List;

/**
 * Contains all business logic to convert between currencies. Supoorted currencies can be
 * queried.
 * Created by superernie77 on 01.06.2017.
 */
public interface ConverterService {

    /**
     * Converts the requested conversion. After execution the traget amount has been set.
     * @param conversion
     * @return
     */
    public Conversion convert(Conversion conversion);

    /**
     * Returns a list of all supported currencies.
     * @return
     */
    public List<String> getCurrencies();
}
