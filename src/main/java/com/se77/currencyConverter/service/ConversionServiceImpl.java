package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.jpa.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by superernie77 on 01.06.2017.
 */
@Component
public class ConversionServiceImpl implements  ConverterService {

    @Autowired
    private Currencylayer currencylayer;

    @Override
    public Conversion convert(Conversion conversion) {

        Double result =  currencylayer.getExchangeRate(conversion.getSourceCurrency(), conversion.getTargetCurrency(), conversion.getSourceAmount(), conversion.getQueryDate());

        conversion.setTargetAmount(result);

        return conversion;
    }

    @Override
    public List<String> getCurrencies() {
        return currencylayer.getCurrencies();
    }
}
