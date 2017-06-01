package com.se77.currencyConverter.controller;

import com.se77.currencyConverter.domain.ConversionBean;
import com.se77.currencyConverter.domain.User;
import com.se77.currencyConverter.repository.ConversionBeanRepository;
import com.se77.currencyConverter.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
        modelAndView.addObject("conversionBean", new ConversionBean());
        modelAndView.setViewName("converter");
        return modelAndView;
    }

    @RequestMapping(value="/converter", method = RequestMethod.POST)
    public ModelAndView convert(@Valid ConversionBean conversionBean, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        ConversionBean result = conversionService.convert(conversionBean);

        convBeanRepo.save(result);

        List<ConversionBean> beans = convBeanRepo.findAll();

        modelAndView.addObject("conversionBean",result);
        modelAndView.addObject("conversionBeans",beans);
        modelAndView.setViewName("converter");
        return modelAndView;
    }
}
