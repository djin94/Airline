package com.foxminded.airline.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ErrorControllerSmokeTest {
    @Autowired
    private ErrorController errorController;

    @Autowired
    private RequestMappingHandlerAdapter handleAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void whenGetError403Page_thenReturnError403Page() throws Exception{
        request.setRequestURI("/403");
        request.setMethod("GET");

        ModelAndView mav = handleAdapter.handle(request, response, handlerMapping.getHandler(request).getHandler());

        ModelAndViewAssert.assertViewName(mav, "error/403");
    }

}