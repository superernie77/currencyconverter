package com.se77.currencyConverter.service;

import java.util.Date;
import java.util.List;

/**
 * Interface to the currencyLayer webservice.
 * Created by superernie77 on 05.06.2017.
 */
public interface Currencylayer {

    /**
     * Calculates a value from a source currency to a target currency for a certain date.
     * @param sourceCurrency currency of the value
     * @param targetCurrency currency to convert to
     * @param value amount in source currency
     * @param date date of the exchange rate
     * @return
     */
    public Double getExchangeAmount(String sourceCurrency, String targetCurrency, Double value, Date date);

    /**
     * Returns a list of all supported currencies.
     * @return
     */
    public List<String> getCurrencies();
}
