package com.se77.currencyConverter.service;

import static org.junit.Assert.*;

import com.se77.currencyConverter.domain.jpa.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Test the operations of the user service implementation.
 * Created by superernie77 on 31.05.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userServie;

    /**
     * Integration test to save and load a user entity
     */
    @Test
    public void testSaveFindUser(){

        User user = new User();
        user.setLastName("Elias");
        user.setFirstName("Ernesto");
        user.setCityName("Freilassing");
        user.setZipcode("12345");
        user.setStreetName("Hauptstr. 5");
        user.setBirthday(new Date());
        user.setEmail("test@test.de");
        user.setPassword("testpassword");

        userServie.saveUser(user);

        // password has been encrypted before save operation
        assertNotEquals(user.getPassword(), "testpassword" );

        user = userServie.findByEmail("test@test.de");

        // previously saved user should be loaded
        Assert.assertNotNull(user);

        // data es loaded/saved successfully
        Assert.assertEquals(user.getLastName(),"Elias" );
        Assert.assertEquals(user.getFirstName(),"Ernesto" );
        Assert.assertEquals(user.getEmail(),"test@test.de" );
    }
}
