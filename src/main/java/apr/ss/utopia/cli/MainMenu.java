package apr.ss.utopia.cli;

import apr.ss.utopia.cli.employee.EmployeeMenu;
import apr.ss.utopia.inputhandler.IntInputHandler;

public class MainMenu implements Menu {

    @Override
    public void startMenu() {
        while (display()) ;
    }

    @Override
    public boolean display() {
        System.out.println(
                "Welcome to the Utopia Airlines Management System. Which category of a user are you\n" +
                        "[1] Employee\n" +
                        "[2] Administrator\n" +
                        "[3] Traveler");
        handleMenuSelection();
        return true;
    }

    private void handleMenuSelection() {
        switch (new IntInputHandler(1, 3).getInput()) {
            case 1:
                new EmployeeMenu().startMenu();
                break;
            case 2:
                // TODO: handle admin
                break;
            case 3:
                // TODO: traveler
                break;
            default:
                break;
        }
    }

}
