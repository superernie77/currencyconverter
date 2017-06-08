package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.currencylayer.Currencies;
import com.se77.currencyConverter.domain.currencylayer.CurrencyLayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Client to access the external currencyLayer webservice.
 * Created by superernie77 on 05.06.2017.
 */
@Component
public class CurrencyLayerClient implements Currencylayer {

    private static final String EXCHANGE_URL = "http://apilayer.net/api/historical";

    private static final String CURRENCY_URL = "http://apilayer.net/api/list";

    private static final String APP_ID = "83fce7f08d1b0c86520438151fab55ca";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final String PARAM_API_KEY = "access_key";

    private static final String PARAM_DATE = "date";

    @Autowired
    private RestTemplate restTemplate;

    /**
     *
     * @param sourceCurrency currency of the value
     * @param targetCurrency currency to convert to
     * @param value amount in source currency
     * @param date date of the exchange rate
     * @return
     */
    public Double getExchangeAmount(String sourceCurrency, String targetCurrency, Double value, Date date) {

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(EXCHANGE_URL)
                .queryParam(PARAM_API_KEY, APP_ID)
                .queryParam(PARAM_DATE, format.format(date));

        CurrencyLayerResponse response = restTemplate.getForObject(builder.build().encode().toUri(), CurrencyLayerResponse.class);

        Map<String, Double> quotes = response.getQuotes();

        Double inUsd =  value / quotes.get("USD"+sourceCurrency);

        Double inTargetCurrency = quotes.get("USD"+targetCurrency) * inUsd;

        inTargetCurrency = Math.round(inTargetCurrency * 100d)/100d;

        return inTargetCurrency;
    }

    public List<String> getCurrencies(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CURRENCY_URL)
                .queryParam(PARAM_API_KEY, APP_ID);

        Currencies currencies = restTemplate.getForObject(builder.build().encode().toUri(), Currencies.class);

        return currencies.getCurrencies().keySet().stream().sorted( (c1,c2) -> c1.compareTo(c2) ).collect(Collectors.toList());
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
