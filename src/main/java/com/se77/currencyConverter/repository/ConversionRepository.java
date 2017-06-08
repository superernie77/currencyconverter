package com.se77.currencyConverter.repository;

import com.se77.currencyConverter.domain.jpa.Conversion;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by superernie77 on 01.06.2017.
 */
public interface ConversionRepository extends PagingAndSortingRepository<Conversion, Integer> {
}
