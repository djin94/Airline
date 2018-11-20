package com.foxminded.airline.web.controller;

import org.junit.Before;
import org.junit.Ignore;
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
public class LoginControllerSmokeTest {

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
    public void whenGetLoginPage_thenReturnLoginPage()throws Exception{
        request.setRequestURI("/login");
        request.setMethod("GET");

        ModelAndView mav = handleAdapter.handle(request, response, handlerMapping.getHandler(request).getHandler());

        ModelAndViewAssert.assertViewName(mav, "login");
    }

    @Test
    public void whenGetUserRegistrationPage_thenReturnUserRegistrationPage() throws Exception {
        request.setRequestURI("/registration");
        request.setMethod("GET");

        ModelAndView mav = handleAdapter.handle(request, response, handlerMapping.getHandler(request).getHandler());

        ModelAndViewAssert.assertViewName(mav, "registration");
    }

    @Test
    public void whenCreateNewUser_thenReturnToMainPageIfUserCreate()throws Exception{
        request.setRequestURI("/registration");
        request.setMethod("POST");
        request.setParameter("login","");
        request.setParameter("password","");
        request.setParameter("email","");
        request.setParameter("phone","");

        ModelAndView mav = handleAdapter.handle(request, response, handlerMapping.getHandler(request).getHandler());

        ModelAndViewAssert.assertViewName(mav, "redirect:/");
    }

    @Test
    public void whenCreateNewUser_thenReturnToRegistrationPageIfUserNotCreate()throws Exception{
        request.setRequestURI("/registration");
        request.setMethod("POST");
        request.setParameter("login","djin94");
        request.setParameter("password","");
        request.setParameter("email","");
        request.setParameter("phone","");

        ModelAndView mav = handleAdapter.handle(request, response, handlerMapping.getHandler(request).getHandler());

        ModelAndViewAssert.assertViewName(mav, "redirect:/registration");
    }
}