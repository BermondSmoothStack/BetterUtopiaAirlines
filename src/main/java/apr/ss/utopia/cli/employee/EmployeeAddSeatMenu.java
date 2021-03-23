package apr.ss.utopia.cli.employee;

import apr.ss.utopia.cli.Menu;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Seat;
import apr.ss.utopia.inputhandler.IntInputHandler;
import apr.ss.utopia.service.SeatService;

public class EmployeeAddSeatMenu implements Menu {

    private final Flight flight;

    public EmployeeAddSeatMenu(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void startMenu() {
        //noinspection StatementWithEmptyBody
        while (display()) ;
    }

    @Override
    public boolean display() {
        System.out.println(
                "Pick the Seat Class you want to add seats of, to your flight:\n" +
                        "[1] First\n" +
                        "[2] Business\n" +
                        "[3] Economy\n" +
                        "[4] Quit to cancel operation");
        Seat seat;
        StringBuilder sb = new StringBuilder("Existing number of seats: ");
        switch (getIntSeatInput()) {
            case 1:
                seat = flight.getFirstClass();
                break;
            case 2:
                seat = flight.getBusinessClass();
                break;
            case 3:
                seat = flight.getEconomyClass();
                break;
            default:
                return false;
        }
        sb.append(seat.getCapacity());
        System.out.println(sb);
        System.out.println("Enter new number of seats: ");
        Integer newSeatCapacity = getIntNewCapacityInput();
        seat.setCapacity(newSeatCapacity);
        updateSeat(seat);

        return false;
    }

    public Integer getIntSeatInput() {
        return new IntInputHandler(1, 4).getInput();
    }

    public Integer getIntNewCapacityInput() {
        return new IntInputHandler(1, 1000).getInput();
    }

    public void updateSeat(Seat seat){
        SeatService ss = new SeatService();
        ss.updateSeat(seat);
    }
}
