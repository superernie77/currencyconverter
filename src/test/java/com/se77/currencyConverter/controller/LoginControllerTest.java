package com.se77.currencyConverter.controller;

import com.se77.currencyConverter.domain.jpa.User;
import com.se77.currencyConverter.service.CountryService;
import com.se77.currencyConverter.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

/**
 * Test the LoginController
 * Created by superernie77 on 08.06.2017.
 */
public class LoginControllerTest {

    private LoginController controller;

    private CountryService countryService;

    private UserService userService;

    @Before
    public void setup(){
        controller = new LoginController();
        countryService = Mockito.mock(CountryService.class);
        controller.setCountryService(countryService);

        userService = Mockito.mock(UserService.class);
        controller.setUserService(userService);
    }

    @Test
    public void testLoginRequest(){
        ModelAndView model =  controller.login();

        // user is set for login
        User user = (User)model.getModel().get("user");
        Assert.assertNotNull(user);
    }

    @Test
    public void testRegistrationRequest(){
        ModelAndView model = controller.registration();

        // user is set for registration
        User user = (User)model.getModel().get("user");
        Assert.assertNotNull(user);
    }

    @Test
    public void testRegisterPositive(){

        // no user with matching email in db yet
        Mockito.when(userService.findByEmail("test@test.de")).thenReturn(null);

        // user with minimal data
        User user = new User();
        user.setEmail("test@test.de");
        user.setPassword("1");

        ModelAndView model = controller.createNewUser(user, Mockito.mock(BindingResult.class));

        Assert.assertNotNull(model.getModel().get("successMessage"));

    }

    @Test
    public void testRegisterNegative(){

        // user exists already in db
        Mockito.when(userService.findByEmail("test@test.de")).thenReturn(new User());

        // user minimal data
        User user = new User();
        user.setEmail("test@test.de");
        user.setPassword("1");


        ModelAndView model = controller.createNewUser(user, Mockito.mock(BindingResult.class));

        Assert.assertNotNull(model.getModel().get("errorMessage"));
    }

}
