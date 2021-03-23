package apr.ss.utopia.cli.employee;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.inputhandler.IntInputHandler;

public class EmployeeFlightManagementMenu implements Menu {

    final Flight selectedFlight;
    public EmployeeFlightManagementMenu(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    @Override
    public void startMenu() {
        while(display());
    }

    @Override
    public boolean display() {
        System.out.println(
                "[1] View more details about the flight\n" +
                "[2] Update the details of the Flight \n" +
                "[3] Add Seats to Flight\n" +
                "[4] Quit to previous");
        return handleMenuSelection();
    }

    private boolean handleMenuSelection(){
        switch(new IntInputHandler(1,4).getInput()){
            case 1:
                new EmployeeViewFlightMenu(selectedFlight).startMenu();
                break;
            case 2:
                new EmployeeUpdateFlightMenu(selectedFlight).startMenu();
                // TODO: Update flight
                break;
            case 3:
                // TODO: Add Seats to flight
                break;
            case 4:
                return false;
            default:
                break;
        }
        return true;
    }
}
