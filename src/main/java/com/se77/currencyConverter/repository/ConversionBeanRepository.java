package com.se77.currencyConverter.repository;

import com.se77.currencyConverter.domain.ConversionBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by superernie77 on 01.06.2017.
 */
public interface ConversionBeanRepository extends JpaRepository<ConversionBean, Integer> {
}
