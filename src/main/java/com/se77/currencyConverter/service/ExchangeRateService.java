package com.se77.currencyConverter.service;

/**
 * Created by superernie77 on 05.06.2017.
 */
public interface ExchangeRateService {

    public Double getExchangeRate(String sourceCurrency, String targetCurrency, Double value);
}
