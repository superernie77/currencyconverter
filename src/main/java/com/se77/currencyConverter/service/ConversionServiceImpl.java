package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.ConversionBean;
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
    public ConversionBean convert(ConversionBean conversionBean) {

        Double result =  currencylayer.getExchangeRate(conversionBean.getSourceCurrency(),conversionBean.getTargetCurrency(),conversionBean.getSourceAmount(), conversionBean.getQueryDate());

        conversionBean.setTargetAmount(result);

        return conversionBean;
    }

    @Override
    public List<String> getCurrencies() {
        return currencylayer.getCurrencies();
    }
}
