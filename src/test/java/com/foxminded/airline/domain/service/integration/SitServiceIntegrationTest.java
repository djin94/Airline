package com.foxminded.airline.domain.service.integration;

import com.foxminded.airline.domain.entity.*;
import com.foxminded.airline.domain.service.SitService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SitServiceIntegrationTest {
    @Autowired
    private SitService sitService;

    private Flight flight;
    private Ticket ticket;
    private List<Sit> planeSits;
    private List<Sit> availablePlaneSits;
    private Sit availableSit;
    private Sit sitWithNullLevelTicket;
    private Sit sitWithBusinessLevelticket;

    @Before
    public void setUp() throws Exception {
        Plane plane = new Plane();
        plane.setId((long) 1);
        plane.setName("Boeing 747");

        flight = new Flight();
        flight.setId((long) 1);
        flight.setPlane(plane);

        Sit busySit = new Sit();
        busySit.setId((long) 1);
        busySit.setPlane(plane);
        busySit.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());

        availableSit = new Sit();
        availableSit.setId((long) 2);
        availableSit.setPlane(plane);
        availableSit.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());

        planeSits = new ArrayList<>();
        planeSits.add(busySit);
        planeSits.add(availableSit);

        availablePlaneSits = new ArrayList<>();
        availablePlaneSits.add(availableSit);

        ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setSit(busySit);

        sitWithNullLevelTicket = new Sit();
        sitWithBusinessLevelticket = new Sit();
        sitWithBusinessLevelticket.setLevelTicket(LevelTicket.BUSINESS.getLevelTicket());
    }

    @Test
    public void whenFindAvailableSitsForFlightAndLevelTicket_thenReturnAvailableSitsForFlight() {
        List<Sit> actualSits = sitService.findAvailableSitsForFlightAndLevelTicket(flight, LevelTicket.ECONOM.getLevelTicket());

        assertThat(actualSits, hasItem(availableSit));
    }

    @Test
    public void whenLevelTicketInSitIsNull_thenReturnDefaultLevelTicket() {
        String expectedLevelTicket = LevelTicket.ECONOM.getLevelTicket();
        String actualLevelTicket = sitService.getLevelTicketFromSitOrDefault(sitWithNullLevelTicket);

        assertEquals(expectedLevelTicket, actualLevelTicket);
    }

    @Test
    public void whenLevelTicketInSitIsBusiness_thenReturnBusinessLevelTicket() {
        String expectedLevelTicket = LevelTicket.BUSINESS.getLevelTicket();
        String actualLevelTicket = sitService.getLevelTicketFromSitOrDefault(sitWithBusinessLevelticket);

        assertEquals(expectedLevelTicket, actualLevelTicket);
    }
}