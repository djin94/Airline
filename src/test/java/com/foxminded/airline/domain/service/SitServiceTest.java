package com.foxminded.airline.domain.service;

import com.foxminded.airline.dao.repository.SitRepository;
import com.foxminded.airline.dao.repository.TicketRepository;
import com.foxminded.airline.domain.entity.*;
import com.foxminded.airline.domain.service.impl.SitServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SitServiceTest {

    @InjectMocks
    private SitServiceImpl sitService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private SitRepository sitRepository;

    private Flight flight;
    private Ticket ticket;
    private List<Sit> planeSits;
    private List<Sit> availablePlaneSits;
    private Sit sitWithNullLevelTicket;
    private Sit sitWithBusinessLevelticket;

    @Before
    public void setUp() throws Exception {
        Plane plane = new Plane();

        flight = new Flight();
        flight.setPlane(plane);

        Sit busySit = new Sit();
        busySit.setId((long) 1);
        busySit.setPlane(plane);
        busySit.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());

        Sit availableSit = new Sit();
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

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenFindAvailableSitsForFlightAndLevelTicket_thenReturnAvailableSitsForFlight() {
        when(sitRepository.findByPlaneAndLevelTicket(flight.getPlane(), LevelTicket.ECONOM.getLevelTicket())).thenReturn(planeSits);
        when(ticketRepository.findByFlight(flight)).thenReturn(asList(ticket));

        List<Sit> expectedSits = availablePlaneSits;
        List<Sit> actualSits = sitService.findAvailableSitsForFlightAndLevelTicket(flight, LevelTicket.ECONOM.getLevelTicket());

        assertEquals(expectedSits, actualSits);
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