package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.ConversionBean;

import java.util.List;

/**
 * Created by superernie77 on 01.06.2017.
 */
public interface ConverterService {

    public ConversionBean convert(ConversionBean conversionBean);

    public List<String> getCurrencies();
}
