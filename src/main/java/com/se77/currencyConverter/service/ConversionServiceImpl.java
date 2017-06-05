package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.ConversionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by superernie77 on 01.06.2017.
 */
@Component
public class ConversionServiceImpl implements  ConverterService {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Override
    public ConversionBean convert(ConversionBean conversionBean) {

        Double result =  exchangeRateService.getExchangeRate(conversionBean.getSourceCurrency(),conversionBean.getTargetCurrency(),conversionBean.getSourceAmount());

        conversionBean.setTargetAmount(result);

        return conversionBean;
    }
}
