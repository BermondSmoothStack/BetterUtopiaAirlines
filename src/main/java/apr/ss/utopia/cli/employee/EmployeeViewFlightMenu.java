package apr.ss.utopia.cli.employee;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;

import java.io.IOException;
import java.time.LocalDateTime;

public class EmployeeViewFlightMenu implements Menu {

    final Flight selectedFlight;

    public EmployeeViewFlightMenu(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    @Override
    public void startMenu() {
        //noinspection StatementWithEmptyBody
        while (display()) ;
        System.out.println("Returning to previous menu...");

    }

    @Override
    public boolean display() {
        Integer I = selectedFlight.getId();
        String A = selectedFlight.getRoute().getOriginAirport().getCity();
        String B = selectedFlight.getRoute().getDestinationAirport().getCity();
        String C = getDate(selectedFlight.getDepartureTime());
        String D = getTime(selectedFlight.getDepartureTime());
        String E = getDate(selectedFlight.getArrivalTime());
        String F = getTime(selectedFlight.getArrivalTime());
        Integer X = selectedFlight.getFirstClass().getAvailable();
        Integer Y = selectedFlight.getBusinessClass().getAvailable();
        Integer Z = selectedFlight.getEconomyClass().getAvailable();

        String sb = "You have chosen to view the Flight with Flight Id: " +
                I +
                " and Departure Airport: " +
                A +
                " and Arrival Airport: " +
                B +
                ". \n\nDeparture Airport: " +
                A +
                "\t|\t Arrival Airport: " +
                B +
                "\nDeparture Date: " +
                C +
                "\t|\t Departure Time: " +
                D +
                "\nArrival Date: " +
                E +
                "\t|\t Arrival Time: " +
                F +
                "\n\nAvailable Seats by Class:\n1) First → " +
                X +
                "\n2) Business → " +
                Y +
                "\n3) Economy → " +
                Z +
                "\nPress Any [Enter] to continue.";

        System.out.println(sb);
        try {
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getDate(LocalDateTime datetime) {
        return datetime.getMonth().toString() + " " + datetime.getDayOfMonth() + ", " + datetime.getYear();
    }

    private String getTime(LocalDateTime dateTime) {
        return dateTime.getHour() + ":" + dateTime.getMinute();
    }
}
