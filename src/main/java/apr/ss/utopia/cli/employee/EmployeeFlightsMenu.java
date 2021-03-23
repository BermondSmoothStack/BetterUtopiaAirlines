package apr.ss.utopia.cli.employee;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.IntInputHandler;

import java.util.List;

public class EmployeeFlightsMenu implements Menu {

    private List<Flight> flights;

    public EmployeeFlightsMenu() {
        // TODO: Fetch flights from database
        this.flights = flights;
        startMenu();
    }

    @Override
    public void startMenu() {
        while (display()) ;
    }

    @Override
    public boolean display() {
        int c = 1;
        System.out.println("Select a flight:");
        for (Flight f : flights) {
            String item = "[" + c + "] " + f.showRoute();
            System.out.println(item);
            c++;
        }
        int i = flights.size() + 1;
        System.out.println("[" + i + "] Quit to cancel operation.");
        return handleFlightSelection(c);
    }

    private boolean handleFlightSelection(Integer selections) {
        Integer input = new IntInputHandler(1, selections).getInput();
        if (input.equals(selections))
            return false;

        Flight selectedFlight = flights.get(input);
        new EmployeeFlightManagementMenu(selectedFlight).startMenu();
        return true;
    }
}
