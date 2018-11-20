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

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketControllerSmokeTest {

    @Autowired
    private RequestMappingHandlerAdapter handleAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    private String number;
    private String dateString;
    private String timeString;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        number = "2587";
        dateString = "2018-10-02";
        timeString = "16:40";
    }

    @Test
    public void whenGetBuyTicketPage_thenReturnBuyTicketPage() throws Exception{
        request.setRequestURI("/buyticket");
        request.setMethod("GET");
        request.setParameter("number", number);
        request.setParameter("dateString", dateString);
        request.setParameter("timeString", timeString);

        ModelAndView mav = handleAdapter.handle(request, response, handlerMapping.getHandler(request).getHandler());

        ModelAndViewAssert.assertViewName(mav, "buyTicket");
    }

    @Test
    public void whenGetBuyTicketPageForUser_thenReturnBuyTicketPageForUser() throws Exception{
        request.setRequestURI("/user/buyticket");
        request.setMethod("GET");
        request.setParameter("number", number);
        request.setParameter("dateString", dateString);
        request.setParameter("timeString", timeString);

        ModelAndView mav = handleAdapter.handle(request, response, handlerMapping.getHandler(request).getHandler());

        ModelAndViewAssert.assertViewName(mav, "user/buyTicket");
    }

    @Test
    public void whenGetPurchasedTicketsPage_thenReturnPurchasedTicketsPage()throws Exception{
        request.setRequestURI("/admin/listtickets");
        request.setMethod("GET");
        request.setParameter("number", number);
        request.setParameter("dateString", dateString);
        request.setParameter("timeString", timeString);

        ModelAndView mav = handleAdapter.handle(request, response, handlerMapping.getHandler(request).getHandler());

        ModelAndViewAssert.assertViewName(mav, "admin/listTickets");
    }
}