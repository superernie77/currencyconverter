package com.se77.currencyConverter.service;

import java.util.Date;
import java.util.List;

/**
 * Created by superernie77 on 05.06.2017.
 */
public interface Currencylayer {

    public Double getExchangeRate(String sourceCurrency, String targetCurrency, Double value, Date date);

    public List<String> getCurrencies();
}
