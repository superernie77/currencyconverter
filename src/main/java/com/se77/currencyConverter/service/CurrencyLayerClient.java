package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.CurrencyLayerResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Created by superernie77 on 05.06.2017.
 */
public class CurrencyLayerClient implements  ExchangeRateService {

    private static final String EXCHANGE_URL = "http://apilayer.net/api/live";

    private static final String APP_ID = "83fce7f08d1b0c86520438151fab55ca";

    public Double getExchangeRate(String sourceCurrency, String targetCurrency, Double value) {


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(EXCHANGE_URL)
            .queryParam("access_key", APP_ID)
            .queryParam("from", sourceCurrency)
            .queryParam("to", targetCurrency)
            .queryParam("amount", value);


        RestTemplate restTemplate = new RestTemplate();
        CurrencyLayerResponse response = restTemplate.getForObject(builder.build().encode().toUri(), CurrencyLayerResponse.class);

        Map<String, Double> quotes = response.getQuotes();

        Double inUsd =  value / quotes.get("USD"+sourceCurrency);

        Double inTargetCurrency = quotes.get("USD"+targetCurrency) * inUsd;

        return inTargetCurrency;
    }
}
