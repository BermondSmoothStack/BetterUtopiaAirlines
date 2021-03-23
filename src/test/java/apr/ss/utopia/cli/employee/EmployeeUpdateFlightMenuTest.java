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
    EmployeeUpdateFlightMenu menu;

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

        menu = new EmployeeUpdateFlightMenu(testFlight);
    }


    @Test
    public void testAssignAirport() {

        EmployeeUpdateFlightMenu mockedMenu = Mockito.spy(menu);

        Airport responseAirport = new Airport();
        responseAirport.setAirportCode("PAR");
        responseAirport.setCity("Paris");

        doReturn("par").when(mockedMenu).getStringInput();
        doReturn(responseAirport).when(mockedMenu).getAirportByCode(any());

        boolean actual = mockedMenu.assignAirport("Origin");
        assertTrue(actual);
    }

    @Test
    public void testAssignAirportSkip() {
        EmployeeUpdateFlightMenu mockedMenu = Mockito.spy(menu);
        doReturn("n/a").when(mockedMenu).getStringInput();

        boolean actual = mockedMenu.assignAirport(any());
        assertTrue(actual);
    }

    @Test
    public void testAssignAirportExit() {
        EmployeeUpdateFlightMenu mockedMenu = Mockito.spy(menu);
        doReturn("quit").when(mockedMenu).getStringInput();

        boolean actual = mockedMenu.assignAirport(any());
        assertFalse(actual);
    }

    @Test
    public void testDisplay(){
        EmployeeUpdateFlightMenu mockedMenu = Mockito.spy(menu);

        Airport responseAirport = new Airport();
        responseAirport.setAirportCode("PAR");
        responseAirport.setCity("Paris");
        doReturn("par").
                doReturn("hou").
                doReturn("07-21-2021").
                doReturn("17:55").
                doReturn("07-22-2021").
                doReturn("17:55").
                when(mockedMenu).getStringInput();
        doReturn(responseAirport).when(mockedMenu).getAirportByCode("par");
        doReturn(responseAirport).when(mockedMenu).getAirportByCode("hou");


        boolean actual = mockedMenu.display();
        assertFalse(actual);


    }

    @Test
    public void testAssignDepartureDate() {
        EmployeeUpdateFlightMenu mockedMenu = Mockito.spy(menu);
        doReturn("07-21-2021").doReturn("17:55").when(mockedMenu).getStringInput();
        boolean actual = mockedMenu.assignFlightDate("Departure");

        assertNotNull(mockedMenu.flight.getDepartureTime());
        assertTrue(actual);

    }

    @Test (expected = DateTimeParseException.class)
    public void testAssignDepartureDateBadFormat() {
        EmployeeUpdateFlightMenu mockedMenu = Mockito.spy(menu);
        doReturn("July 21 2021").doReturn("17:55").when(mockedMenu).getStringInput();
        mockedMenu.assignFlightDate("Departure");
    }
}