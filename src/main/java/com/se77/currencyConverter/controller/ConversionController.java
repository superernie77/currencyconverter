package com.se77.currencyConverter.controller;

import com.se77.currencyConverter.domain.jpa.Conversion;
import com.se77.currencyConverter.repository.ConversionRepository;
import com.se77.currencyConverter.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Spring MVC-controller for the currency conversion screen.
 * Created by superernie77 on 01.06.2017.
 */
@Controller
public class ConversionController {

    @Autowired
    private ConverterService conversionService;

    @Autowired
    private ConversionRepository convBeanRepo;

    private PageRequest page = new PageRequest(
            0, 10, Sort.Direction.DESC, "id"
    );

    /**
     * Init-method for the main converter screen
     */
    @RequestMapping(value="/converter", method = RequestMethod.GET)
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();

        addConversionHistory(modelAndView);

        Conversion conversion = new Conversion();
        conversion.setQueryDate(new Date()); // default date is today
        conversion.setSourceAmount(0d);
        modelAndView.addObject("conversion", conversion);

        // currency selection
        List<String> currencies = conversionService.getCurrencies();
        modelAndView.addObject("currencies",currencies);

        modelAndView.setViewName("converter");

        return modelAndView;
    }

    /**
     * Processes the conversion request.
     * @param conversion conversion parameters
     */
    @RequestMapping(value="/converter", method = RequestMethod.POST)
    public ModelAndView convert(@Valid Conversion conversion, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("converter");

        // populate currency list
        List<String> currencies = conversionService.getCurrencies();
        modelAndView.addObject("currencies",currencies);

        // return if there are validation errors
        if(bindingResult.hasErrors()){
            // find past conversions
            addConversionHistory(modelAndView);
            return modelAndView;
        }

        // perform the conversion
        Conversion result = conversionService.convert(conversion);
        convBeanRepo.save(result);
        modelAndView.addObject("conversion",result);

        addConversionHistory(modelAndView);

        return modelAndView;
    }

    private void addConversionHistory(ModelAndView modelAndView){
        Page<Conversion> conversions = convBeanRepo.findAll(page);
        if (conversions.getNumberOfElements() > 0) {
            modelAndView.addObject("conversions", conversions);
        }
    }

    public void setConvBeanRepo(ConversionRepository convBeanRepo) {
        this.convBeanRepo = convBeanRepo;
    }

    public void setConversionService(ConverterService conversionService) {
        this.conversionService = conversionService;
    }
}
