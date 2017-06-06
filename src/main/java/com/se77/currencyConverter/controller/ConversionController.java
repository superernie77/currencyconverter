package com.se77.currencyConverter.controller;

import com.se77.currencyConverter.domain.ConversionBean;
import com.se77.currencyConverter.domain.User;
import com.se77.currencyConverter.repository.ConversionBeanRepository;
import com.se77.currencyConverter.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by superernie77 on 01.06.2017.
 */
@Controller
public class ConversionController {

    @Autowired
    private ConverterService conversionService;

    @Autowired
    private ConversionBeanRepository convBeanRepo;

    @RequestMapping(value="/converter", method = RequestMethod.GET)
    public ModelAndView converter(){
        ModelAndView modelAndView = new ModelAndView();

        Page<ConversionBean> conversions = convBeanRepo.findAll(new PageRequest(0, 10));

        List<String> currencies = conversionService.getCurrencies();

        modelAndView.addObject("conversionBeans",conversions);

        ConversionBean conversionBean = new ConversionBean();
        conversionBean.setQueryDate(new Date());
        modelAndView.addObject("conversionBean", conversionBean );

        modelAndView.addObject("currencies",currencies);

        modelAndView.setViewName("converter");

        return modelAndView;
    }

    @RequestMapping(value="/converter", method = RequestMethod.POST)
    public ModelAndView convert(@Valid ConversionBean conversionBean){
        ModelAndView modelAndView = new ModelAndView();

        ConversionBean result = conversionService.convert(conversionBean);

        convBeanRepo.save(result);

        PageRequest page = new PageRequest(
                0, 10, Sort.Direction.ASC, "id"
        );

        Page<ConversionBean> conversions = convBeanRepo.findAll(page);

        List<String> currencies = conversionService.getCurrencies();

        modelAndView.addObject("conversionBeans",conversions);

        modelAndView.addObject("conversionBean",result);

        modelAndView.addObject("currencies",currencies);

        modelAndView.setViewName("converter");

        return modelAndView;
    }

    public void setConvBeanRepo(ConversionBeanRepository convBeanRepo) {
        this.convBeanRepo = convBeanRepo;
    }

    public void setConversionService(ConverterService conversionService) {
        this.conversionService = conversionService;
    }
}
