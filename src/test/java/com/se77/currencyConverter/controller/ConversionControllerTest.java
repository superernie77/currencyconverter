package com.se77.currencyConverter.controller;

import com.se77.currencyConverter.domain.ConversionBean;
import com.se77.currencyConverter.repository.ConversionBeanRepository;
import com.se77.currencyConverter.service.ConverterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by superernie77 on 05.06.2017.
 */
public class ConversionControllerTest {

    private ConversionController controller;

    private ConversionBeanRepository conBeanRepo;

    private ConverterService conversionService;

    @Before
    public void setup(){
        controller = new ConversionController();

        conBeanRepo =  Mockito.mock(ConversionBeanRepository.class);
        conversionService = Mockito.mock(ConverterService.class);

        controller.setConvBeanRepo(conBeanRepo);
        controller.setConversionService(conversionService);
    }

    @Test
    public void testConverterModelAndView(){
        Mockito.when(conBeanRepo.findAll(Mockito.any(PageRequest.class))).thenReturn(Mockito.mock(Page.class));

        ModelAndView modelAndView = controller.converter();

        Mockito.verify(conBeanRepo).findAll(Mockito.any(PageRequest.class));

        // new conversion is set
        Assert.assertNotNull(modelAndView.getModel().get("conversionBean"));

    }

    @Test
    public void testConvertModelAndView(){

        ConversionBean conversionBean = new ConversionBean();
        Mockito.when(conversionService.convert(conversionBean)).thenReturn(conversionBean);
        Mockito.when(conBeanRepo.findAll(Mockito.any(PageRequest.class))).thenReturn(Mockito.mock(Page.class));

        ModelAndView modelAndView = controller.convert(conversionBean);

        Mockito.verify(conversionService).convert(conversionBean);
        Mockito.verify(conBeanRepo).findAll(Mockito.any(PageRequest.class));

        // new conversion result is set
        Assert.assertNotNull(modelAndView.getModel().get("conversionBean"));

    }
}
