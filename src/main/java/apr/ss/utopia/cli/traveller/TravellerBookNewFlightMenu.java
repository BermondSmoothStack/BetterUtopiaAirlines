package apr.ss.utopia.cli.traveller;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.entity.Seat;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.service.FlightService;
import apr.ss.utopia.service.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TravellerBookNewFlightMenu implements Menu {

    final Passenger passenger;

    public TravellerBookNewFlightMenu(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public void startMenu() {
        //noinspection StatementWithEmptyBody
        while (display()) ;
        System.out.println("Returning to previous menu...");
    }

    @Override
    public boolean display() {
        List<Flight> flights = filteredFlights(fetchAllFlights());
        StringBuilder sb = new StringBuilder("Pick the flight you want to book a ticket for:\n");

        int count = 1;
        for (Flight flight : flights) {
            sb.append("[").append(count).append("] ").append(flight.showRoute())
                    .append("\n");
            count++;
        }

        System.out.println(sb);
        Flight selectedFlight = flights.get(getMenuInput(count) - 1);

        StringBuilder sb2 = new StringBuilder("Pick the Seat you want to book a ticket for");
        List<Seat> seats = new ArrayList<>();

        int count2 = 1;
        if (selectedFlight.getFirstClass().getAvailable() > 0) {
            seats.add(selectedFlight.getFirstClass());
            sb2.append("\n[").append(count2).append("] First Class");
            count2++;
        }
        if (selectedFlight.getBusinessClass().getAvailable() > 0) {
            seats.add(selectedFlight.getBusinessClass());
            sb2.append("\n[").append(count2).append("] Business Class");
            count2++;
        }
        if (selectedFlight.getEconomyClass().getAvailable() > 0) {
            seats.add(selectedFlight.getEconomyClass());
            sb2.append("\n[").append(count2).append("] Economy Class");
        }

        System.out.println(sb2.toString());
        Integer seatInput = getMenuInput((count2));
        Seat selectedSeat = seats.get(seatInput - 1);
        selectedSeat.setReserved(selectedSeat.getReserved() + 1);

        createNewTicket(passenger, selectedSeat);
        return false;
    }

    public List<Flight> fetchAllFlights() {
        FlightService fs = new FlightService(); // TODO: fetch all flights
        return fs.fetchAllFlights();
    }

    public List<Flight> filteredFlights(List<Flight> flights) {
        return flights.stream().filter(flight ->

                flight.getFirstClass().getAvailable() > 0 ||
                        (flight.getBusinessClass().getAvailable() > 0 ||
                                (flight.getEconomyClass().getAvailable() > 0))

        ).collect(Collectors.toList());
    }

    public Integer getMenuInput(Integer max) {
        return new IntInputHandler(1, max).getInput(); // TODO: Create ticket
    }

    public void createNewTicket(Passenger p, Seat s){
        TicketService ts = new TicketService(); //TODO: Create ticket
        ts.createTicket(p, s);
    }
}
