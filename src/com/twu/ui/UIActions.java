package com.twu.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIActions {
    private final Scanner scanner;
    private static PrintStream printStream;

    public UIActions(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
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

    public static void print(String string){
        printStream.println(string);
    }

    public void printSpace(){
        printStream.println();
    }
}
