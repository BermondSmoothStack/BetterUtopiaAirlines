package apr.ss.utopia.inputhandler;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class AbsIntInputHandler implements IInputHandler<Integer> {

    protected Integer verifiedInput;
    protected Integer min = 0;
    protected Integer max = 100;

    public Integer scanInput() throws NoSuchElementException {

        Integer intInput;
        try {
            Scanner input = new Scanner(System.in);
            intInput = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter an integer");
            intInput = scanInput();
        }

        verifiedInput = intInput;
        return intInput;
    }

    public Integer getInput() {
        int input = scanInput();

        while (!verifyInputRange(input)) {
            try {
                System.out.println("Please enter an integer between[" + getMin() + ", " + getMax() + "]");
                input = scanInput();
            } catch (NoSuchElementException e) {
                System.out.println("Please enter an integer between[" + getMin() + ", " + getMax() + "]");
            }
        }

        return input;
    }

    protected boolean verifyInputRange(Integer input) {
        return ((input >= getMin()) && (input <= getMax()));
    }

    protected abstract Integer getMin();

    protected abstract Integer getMax();

    public Integer getVerifiedInput() {
        return verifiedInput;
    }


}
