package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.ConversionBean;
import org.springframework.stereotype.Component;

/**
 * Created by superernie77 on 01.06.2017.
 */
@Component
public class ConversionServiceImpl implements  ConverterService {
    @Override
    public ConversionBean convert(ConversionBean conversionBean) {
        conversionBean.setTargetAmount(42d);
        return conversionBean;
    }
}
