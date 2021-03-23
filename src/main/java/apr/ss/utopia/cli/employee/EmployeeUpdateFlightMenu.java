package apr.ss.utopia.cli.employee;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Route;
import apr.ss.utopia.inputhandler.StringInputHandler;
import apr.ss.utopia.service.AirportService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class EmployeeUpdateFlightMenu implements Menu {

    private final String QUIT = "quit";
    private final String NA = "n/a";

    final Flight flight;

    public EmployeeUpdateFlightMenu(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void startMenu() {
        while (display()) ;
    }

    @Override
    public boolean display() {
        boolean proceed = false;
        System.out.println("You have chosen to update the Flight with" +
                "\nFlight ID: " + flight.getId() +
                "\nFlight Origin: " + flight.getRoute().getOriginAirport().getCity() +
                "\nFlight Destination: " + flight.getRoute().getDestinationAirport().getCity() + ". \n" +
                "\nEnter ‘quit’ at any prompt to cancel operation.");

        while (!proceed) {
            if (!assignAirport("Origin")) {
                return false;
            } else {
                proceed = true;
            }
        }

        proceed = false;
        while (!proceed) {
            if (!assignAirport("Destination")) {
                return false;
            } else {
                proceed = true;
            }
        }
        proceed = false;
        while(!proceed){
            try {
                if (!assignDepartureDate()) {
                    return false;
                } else {
                    proceed = true;
                }
            } catch (DateTimeParseException e){
                System.out.println("Date and time format not recognized, please follow the correct format.");
            }
        }

        return false;
    }

    public boolean assignAirport(String tag) {
        Route route = flight.getRoute();

        System.out.println("Please enter a new " + tag + " Airport or enter " + NA + " for no change.");
        String input = getStringInput();

        if (input.equalsIgnoreCase(QUIT))
            return false;
        if (NA.equalsIgnoreCase(input))
            return true;

        Airport oa = getAirportByCode(input); // TODO fetch origin airport by IATA (input)

        if (null == oa || null == oa.getAirportCode()) {
            System.out.println("Can't find Airport!");
            return assignAirport(tag);
        } else {
            if ("Origin".equalsIgnoreCase(tag))
                route.setOriginAirport(oa);
            else
                route.setDestinationAirport(oa);
            flight.setRoute(route);
            return true;
        }
    }

    public boolean assignDepartureDate() throws DateTimeParseException {
        System.out.println("Please enter new Departure Date (mm-dd-yyyy) or enter " + NA + " for no change:\n");
        String dateStr = getStringInput();

        if (dateStr.equalsIgnoreCase(QUIT))
            return false;
        if (NA.equalsIgnoreCase(dateStr))
            return true;

        System.out.println("Please enter a new Departure Time (24h Format) or enter " + NA + " for no change.");
        String timeStr = getStringInput();
        if (NA.equalsIgnoreCase(timeStr))
            return false;
        if (QUIT.equalsIgnoreCase(timeStr))
            return true;

        String dateTimeStr = dateStr + " " + timeStr;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime date = LocalDateTime.parse(dateTimeStr, dtf);
        flight.setDepartureTime(date);
        return true;

    }

    public String getStringInput() {
        return new StringInputHandler().getInput();
    }

    public Airport getAirportByCode(String code) {
        AirportService as = new AirportService();
        return as.fetchAirportByIATA(code);
    }

}

