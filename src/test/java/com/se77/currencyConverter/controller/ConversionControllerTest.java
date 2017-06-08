package com.se77.currencyConverter.controller;

import com.se77.currencyConverter.domain.jpa.Conversion;
import com.se77.currencyConverter.repository.ConversionRepository;
import com.se77.currencyConverter.service.ConverterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

/**
 * Tests the ConversionController
 * Created by superernie77 on 05.06.2017.
 */
public class ConversionControllerTest {

    private ConversionController controller;

    private ConversionRepository conBeanRepo;

    private ConverterService conversionService;

    @Before
    public void setup(){
        controller = new ConversionController();

        conBeanRepo =  Mockito.mock(ConversionRepository.class);
        conversionService = Mockito.mock(ConverterService.class);

        controller.setConvBeanRepo(conBeanRepo);
        controller.setConversionService(conversionService);
    }

    @Test
    public void testInitConvertCurrency(){
        Mockito.when(conBeanRepo.findAll(Mockito.any(PageRequest.class))).thenReturn(Mockito.mock(Page.class));

        ModelAndView modelAndView = controller.init();

        // history was queried
        Mockito.verify(conBeanRepo).findAll(Mockito.any(PageRequest.class));

        // new conversion bean is set for next conversion
        Conversion conv = (Conversion)modelAndView.getModel().get("conversion");
        Assert.assertNull(conv.getTargetAmount()); // no result set yet

    }

    @Test
    public void testConvertPositive(){

        Conversion conversion = new Conversion();
        conversion.setTargetAmount(42d);
        // mock conversion and previous results
        Mockito.when(conversionService.convert(conversion)).thenReturn(conversion);

        // mock history
        Mockito.when(conBeanRepo.findAll(Mockito.any(PageRequest.class))).thenReturn(Mockito.mock(Page.class));

        ModelAndView modelAndView = controller.convert(conversion, Mockito.mock(BindingResult.class));

        // conversion was executed
        Mockito.verify(conversionService).convert(conversion);

        // history set
        Mockito.verify(conBeanRepo).findAll(Mockito.any(PageRequest.class));

        // new conversion result is set
        Conversion conv = (Conversion)modelAndView.getModel().get("conversion");
        Assert.assertTrue(conv.getTargetAmount() == 42d);
    }

    @Test
    public void testConvertBindingErrors(){
        Conversion conversion = new Conversion();
        conversion.setTargetAmount(42d);

        // mock history
        Mockito.when(conBeanRepo.findAll(Mockito.any(PageRequest.class))).thenReturn(Mockito.mock(Page.class));

        // mock binding errors
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = controller.convert(conversion, bindingResult);

        // conversion was NOT executed
        Mockito.verify(conversionService, Mockito.never()).convert(conversion);

        // history set
        Mockito.verify(conBeanRepo).findAll(Mockito.any(PageRequest.class));
    }

}
