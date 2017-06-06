package com.se77.currencyConverter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

/**
 * Created by superernie77 on 06.06.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currencies {

    private HashMap<String, String> currencies ;

    public HashMap<String, String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(HashMap<String, String> currencies) {
        this.currencies = currencies;
    }
}
