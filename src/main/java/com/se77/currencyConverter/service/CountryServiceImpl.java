package com.se77.currencyConverter.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by superernie77 on 06.06.2017.
*/
@Component
public class CountryServiceImpl implements CountryService {
    @Override
    public List<String> getCountries() {
        return Arrays.stream(Locale.getISOCountries()).map( l -> new Locale("",l)).map( c -> c.getDisplayCountry()).sorted().collect(Collectors.toList());
    }
}
