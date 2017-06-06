package com.se77.currencyConverter.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.se77.currencyConverter.domain.User;
import com.se77.currencyConverter.service.CountryService;
import com.se77.currencyConverter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = {"/", "/login"}, method = GET)
    public ModelAndView login() {
        //TODO entfernen
        User user = new User();
        user.setEmail("admin1@test.de");
        user.setPassword("1");
        userService.saveUser(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        user = new User();
        modelAndView.addObject(user);
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();

        List<String> countries = countryService.getCountries();

        modelAndView.addObject("countries", countries);

        modelAndView.addObject("user", user);

        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");

        if(bindingResult.hasErrors()){
            return modelAndView;
        }

        userService.saveUser(user);
        modelAndView.addObject("successMessage", "User has been registered successfully");
        modelAndView.addObject("user", new User());

        return modelAndView;
    }


}