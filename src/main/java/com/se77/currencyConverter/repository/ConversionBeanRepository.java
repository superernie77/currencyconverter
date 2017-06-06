package com.se77.currencyConverter.repository;

import com.se77.currencyConverter.domain.ConversionBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by superernie77 on 01.06.2017.
 */
public interface ConversionBeanRepository extends PagingAndSortingRepository<ConversionBean, Integer> {
}
