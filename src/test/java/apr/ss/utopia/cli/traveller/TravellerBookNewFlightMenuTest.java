package apr.ss.utopia.cli.traveller;

import apr.ss.utopia.entity.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TravellerBookNewFlightMenuTest extends TestCase {

    @Mock
    TravellerBookNewFlightMenu menu;

    @Before
    public void setUp(){
        Passenger p = new Passenger();
        p.setGivenName("givenTest");
        p.setFamilyName("famTest");
        p.setAddress("addrTest");
        p.setId(9999);
        p.setGender("genderTest");
        p.setDob(LocalDate.now());

        menu = new TravellerBookNewFlightMenu(p);

    }

    @Test
    public void testFilteredFlights() {
        TravellerBookNewFlightMenu tbnf = new TravellerBookNewFlightMenu(any());
        List<Flight> filteredFlights = tbnf.filteredFlights(stubbedFlightList());
        assertEquals(3, filteredFlights.size());
    }

    @Test
    public void testDisplay() {
        TravellerBookNewFlightMenu mockedMenu = spy(menu);
        List<Flight> stubbedFlights = stubbedFlightList();

        doReturn(1).doReturn(1).when(mockedMenu).getMenuInput(any());
        doNothing().when(mockedMenu).createNewTicket(any(), any());
        doReturn(stubbedFlights).when(mockedMenu).fetchAllFlights();

        assertFalse(mockedMenu.display());
    }

    public List<Flight> stubbedFlightList(){
        Seat s1 = new Seat();
        Seat s2 = new Seat();
        Seat s3 = new Seat();
        Seat s4 = new Seat();
        Seat s5 = new Seat();
        Seat s6 = new Seat();
        Seat s7 = new Seat();
        Seat s8 = new Seat();
        Seat s9 = new Seat();
        Seat s10 = new Seat();
        Seat s11 = new Seat();
        Seat s12 = new Seat();

        Flight f1 = new Flight();
        Flight f2 = new Flight();
        Flight f3 = new Flight();
        Flight f4 = new Flight();

        Route r1 = new Route();
        Route r2 = new Route();
        Route r3 = new Route();
        Route r4 = new Route();

        Airport a1 = new Airport();
        Airport a2 = new Airport();

        a1.setAirportCode("TS1");
        a1.setCity("Test 1");
        a2.setAirportCode("TS2");
        a2.setCity("Test 2");

        r1.setDestinationAirport(a1);
        r2.setDestinationAirport(a2);
        r3.setDestinationAirport(a1);
        r4.setDestinationAirport(a2);

        r1.setOriginAirport(a2);
        r2.setOriginAirport(a1);
        r3.setOriginAirport(a2);
        r4.setOriginAirport(a1);

        s1.setCapacity(10);
        s2.setCapacity(10);
        s5.setCapacity(10);
        s7.setCapacity(10);
        s11.setCapacity(10);

        s1.setReserved(10);
        s2.setReserved(10);
        s5.setReserved(10);
        s7.setReserved(10);
        s11.setReserved(10);

        s3.setCapacity(10);
        s4.setCapacity(10);
        s6.setCapacity(10);
        s8.setCapacity(10);
        s9.setCapacity(10);
        s10.setCapacity(10);
        s12.setCapacity(10);

        s3.setReserved(0);
        s4.setReserved(10);
        s6.setReserved(10);
        s8.setReserved(0);
        s9.setReserved(0);
        s10.setReserved(0);
        s12.setReserved(0);

        f1.setFirstClass(s1);
        f2.setFirstClass(s4);
        f3.setFirstClass(s7);
        f4.setFirstClass(s10);

        f1.setBusinessClass(s2);
        f2.setBusinessClass(s5);
        f3.setBusinessClass(s8);
        f4.setBusinessClass(s11);

        f1.setEconomyClass(s3);
        f2.setEconomyClass(s6);
        f3.setEconomyClass(s9);
        f4.setEconomyClass(s12);

        f1.setRoute(r1);
        f2.setRoute(r2);
        f3.setRoute(r2);
        f4.setRoute(r1);

        List<Flight> flights = new ArrayList<>();
        flights.add(f1);
        flights.add(f2);
        flights.add(f3);
        flights.add(f4);

        return flights;
    }
}