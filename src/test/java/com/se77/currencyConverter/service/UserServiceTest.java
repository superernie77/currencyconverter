package com.se77.currencyConverter.service;

import static org.junit.Assert.*;

import com.se77.currencyConverter.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by superernie77 on 31.05.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userServie;

    @Test
    public void testSaveUser(){

        User user = new User();
        user.setLastName("Elias");
        user.setFirstname("Ernesto");
        user.setEmail("test@test.de");
        user.setPassword("testpassword");

        userServie.saveUser(user);

        // password has been encrypted before save operation
        assertNotEquals(user.getPassword(), "testpassword" );

    }


}
