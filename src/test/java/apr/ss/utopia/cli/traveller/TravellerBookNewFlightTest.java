package apr.ss.utopia.cli.traveller;

import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Seat;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class TravellerBookNewFlightTest extends TestCase {

    @Test
    public void testFilteredFlights() {
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

        List<Flight> flights = new ArrayList<>();
        flights.add(f1);
        flights.add(f2);
        flights.add(f3);
        flights.add(f4);

        TravellerBookNewFlight tbnf = new TravellerBookNewFlight(any());

        List<Flight> filteredFlights = tbnf.filteredFlights(flights);

        assertEquals(3, filteredFlights.size());

    }
}