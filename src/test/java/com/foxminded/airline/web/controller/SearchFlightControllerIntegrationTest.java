package com.foxminded.airline.web.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchFlightControllerIntegrationTest {
    @Autowired
    private SearchFlightController searchFlightController;

    private MockMvc mvc;


    @Before
    public void setUp() throws Exception {

    }

}