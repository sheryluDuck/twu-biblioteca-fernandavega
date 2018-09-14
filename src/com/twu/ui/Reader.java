package com.twu.ui;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Reader {
    private final Scanner scanner;

    public Reader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String readUserInputFromConsole(){

        String inputString = scanner.nextLine();
        return inputString;
    }

    public int castNumberFromConsole(String rawNumber){
        try {
            return Integer.parseInt(rawNumber);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("This is not a number :(");
        }
    }

}
