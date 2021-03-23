package apr.ss.utopia.cli.employee;

import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Route;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeUpdateFlightMenuTest {

    @Mock
    EmployeeUpdateFlightMenu eufm;

    @Before
    public void setUp() {
        Flight testFlight = new Flight();
        Route testRoute = new Route();
        Airport testOriginAirport = new Airport();
        Airport testDestAirport = new Airport();

        testOriginAirport.setAirportCode("NYC");
        testOriginAirport.setCity("New York");

        testDestAirport.setAirportCode("HOU");
        testDestAirport.setCity("Houston");

        testRoute.setOriginAirport(testOriginAirport);
        testRoute.setDestinationAirport(testDestAirport);

        testRoute.setId(998);
        testFlight.setId(999);
        testFlight.setRoute(testRoute);

        eufm = new EmployeeUpdateFlightMenu(testFlight);
    }


    @Test
    public void testAssignAirport() {

        EmployeeUpdateFlightMenu mockEufm = Mockito.spy(eufm);

        Airport responseAirport = new Airport();
        responseAirport.setAirportCode("PAR");
        responseAirport.setCity("Paris");

        doReturn("par").when(mockEufm).getStringInput();
        doReturn(responseAirport).when(mockEufm).getAirportByCode(any());

        boolean actual = mockEufm.assignAirport("Origin");
        assertTrue(actual);
    }

    @Test
    public void testAssignAirportSkip() {
        EmployeeUpdateFlightMenu mockEufm = Mockito.spy(eufm);
        doReturn("n/a").when(mockEufm).getStringInput();

        boolean actual = mockEufm.assignAirport(any());
        assertTrue(actual);
    }

    @Test
    public void testAssignAirportExit() {
        EmployeeUpdateFlightMenu mockEufm = Mockito.spy(eufm);
        doReturn("quit").when(mockEufm).getStringInput();

        boolean actual = mockEufm.assignAirport(any());
        assertFalse(actual);
    }

    @Test
    public void testDisplay(){
        EmployeeUpdateFlightMenu mockEufm = Mockito.spy(eufm);

        Airport responseAirport = new Airport();
        responseAirport.setAirportCode("PAR");
        responseAirport.setCity("Paris");
        doReturn("par").
                doReturn("hou").
                doReturn("07-21-2021").
                doReturn("17:55").
                when(mockEufm).getStringInput();
        doReturn(responseAirport).when(mockEufm).getAirportByCode("par");


        boolean actual = mockEufm.display();
        assertFalse(actual);


    }

    @Test
    public void testAssignDepartureDate() {
        EmployeeUpdateFlightMenu mockEufm = Mockito.spy(eufm);
        doReturn("07-21-2021").doReturn("17:55").when(mockEufm).getStringInput();
        boolean actual = mockEufm.assignDepartureDate();

        assertNotNull(mockEufm.flight.getDepartureTime());
        assertTrue(actual);

    }

    @Test (expected = DateTimeParseException.class)
    public void testAssignDepartureDateBadFormat() {
        EmployeeUpdateFlightMenu mockEufm = Mockito.spy(eufm);
        doReturn("July 21 2021").doReturn("17:55").when(mockEufm).getStringInput();
        mockEufm.assignDepartureDate();
    }
}