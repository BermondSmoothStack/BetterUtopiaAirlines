package apr.ss.utopia.cli.employee;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.inputhandler.IntInputHandler;

public class EmployeeMenu implements Menu {

    @Override
    public void startMenu() {
        //noinspection StatementWithEmptyBody
        while(display());
        System.out.println("Returning to previous menu...");
    }

    @Override
    public boolean display() {
            System.out.println("Employee\n" +
                    "[1] Enter flights you manage.\n" +
                    "[2] Quit to previous.");
            return handleMenuSelection();
    }

    private boolean handleMenuSelection() {
        switch (new IntInputHandler(1, 2).getInput()) {
            case 1:
                new EmployeeFlightsMenu().startMenu();
                break;
            case 2:
                return false;
            default:
                break;
        }
        return true;
    }
}
