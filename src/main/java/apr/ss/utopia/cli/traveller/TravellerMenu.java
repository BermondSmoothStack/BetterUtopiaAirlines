package apr.ss.utopia.cli.traveller;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.service.PassengerService;

public class TravellerMenu implements Menu {

    @Override
    public void startMenu() {
        //noinspection StatementWithEmptyBody
        while (display()) ;
        System.out.println("Returning to previous menu...");

    }

    @Override
    public boolean display() {
        System.out.println("Enter the your Membership Number:\n");
        Integer membershipNumber = getMembershipNumber();
        Passenger passenger = fetchPassengerById(membershipNumber); // TODO: Fetch passenger

        if (null == passenger.getId()) {
            System.out.println("Could not find membership number. Please Try again.");
            return true;
        }

        System.out.println(
                "[1] Book a Ticket\n" +
                        "[2] Cancel an Upcoming Trip\n" +
                        "[3] Quit to Previous\n"
        );
        switch(getMenuInput()){
            case 1:
                new TravellerBookNewFlightMenu(passenger).startMenu();
                break;
            case 2:
                // TODO: Cancel booking
                break;
            case 3:
                return false;
        }

        return false;
    }

    public Integer getMenuInput() {
        return new IntInputHandler(1, 3).getInput();
    }

    public Integer getMembershipNumber() {
        return new IntInputHandler(1, 9999999).getInput();
    }

    public Passenger fetchPassengerById(Integer id) {
        PassengerService ps = new PassengerService();
        return ps.getPassengerById(id);
    }
}
