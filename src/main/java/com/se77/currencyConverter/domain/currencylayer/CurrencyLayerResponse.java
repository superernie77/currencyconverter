package com.se77.currencyConverter.domain.currencylayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Domain object to store the JSON response of the currencyLayer webservice
 * Created by superernie77 on 05.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyLayerResponse {

    private Map<String, Double> quotes;

    public Map<String, Double> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, Double> quotes) {
        this.quotes = quotes;
    }
}
