package apr.ss.utopia.inputhandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StringInputHandler implements IInputHandler<String>{
    protected String verifiedInput;

    public String scanInput(){
        Scanner input = new Scanner(System.in);
        String strInput;

        try {
            strInput = input.nextLine();
        } catch (InputMismatchException e){
            System.out.println("Please enter a String");
            strInput = scanInput();
        }

        verifiedInput = strInput;
        return strInput;
    }

    @Override
    public String getInput() {
        return scanInput();
    }


    public String getVerifiedInput(){ return verifiedInput;}
}
