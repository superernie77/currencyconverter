package com.se77.currencyConverter.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;


import com.se77.currencyConverter.domain.jpa.User;
import com.se77.currencyConverter.service.CountryService;
import com.se77.currencyConverter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Spring MVC-Controller class for the login and registration screen.
 */
@Controller
public class LoginController {

    private static final String ATTR_COUNTRIES = "countries";
    private static final String ATTR_USER = "user";
    private static final String ATTR_OK = "successMessage";
    private static final String ATTR_NOTOK = "errorMessage";

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    /**
     * Serves the request for the login screen.
     * @return
     */
    @RequestMapping(value = {"/", "/login"}, method = GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        User user = new User();
        modelAndView.addObject(ATTR_USER, user);

        return modelAndView;
    }

    /**
     * Serves the request for the registration screen.
     * @return
     */
    @RequestMapping(value = "/registration", method = GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");

        List<String> countries = countryService.getCountries();
        modelAndView.addObject(ATTR_COUNTRIES, countries);

        User user = new User();
        modelAndView.addObject(ATTR_USER, user);

        return modelAndView;
    }

    /**
     * Registeres a new user.
     * @param user user to register

     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/registration", method = POST)
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");

        List<String> countries = countryService.getCountries();
        modelAndView.addObject(ATTR_COUNTRIES, countries);

        // return validation errors
        if(bindingResult.hasErrors()){
            return modelAndView;
        }

        // check is user already exists
        if (userService.findByEmail(user.getEmail()) != null){
            modelAndView.addObject(ATTR_USER, user);
            modelAndView.addObject(ATTR_NOTOK, "Email address is already taken.");
            return modelAndView;
        }

        // save new user
        userService.saveUser(user);
        modelAndView.addObject(ATTR_OK, "User has been registered successfully.");
        modelAndView.addObject(ATTR_USER, new User());

        return modelAndView;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}