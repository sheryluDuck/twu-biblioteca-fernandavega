package com.twu.ui;

import com.twu.book.Book;
import com.twu.library.Library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UIActions {
    static final Scanner scanIn = new Scanner(System.in);
    public void printLibraryBooks(Library library){

        System.out.format("|%-2s|%-20s|%-50s|%-5s|%n", "N", "Author", "Book", "Year");
        int counter = 0;
        for (Book book:
                library.getLibraryBookList()) {
            counter++;
            System.out.format("|%-2d|%-20s|%-50s|%-5d|%n",counter, book.author, book.bookName, book.publishYear);
        }
    }
    private int readNumberFromConsole(String messageToDisplay){
        int selectedBookNumber;
        System.out.println(messageToDisplay);
        String inputString = scanIn.nextLine();
        selectedBookNumber= castNumberFromConsole(inputString);
        return selectedBookNumber;
    }

    public void printSelectedBook(Library library){
        try{
            int selectedNumber= readNumberFromConsole("Please enter a book number: ");
            Book selectedBook = selectLibraryBook(library, selectedNumber);
            System.out.println("Selected Book: ");
            System.out.println(selectedBook.toString());
        }catch (InputMismatchException e){
            System.err.println(e.getMessage());
        }

    }

    private void printCheckOutBook(Library library){
        try{
            int selectedNumber= readNumberFromConsole("Please enter a book number: ");
            Book selectedBook = selectLibraryBook(library, selectedNumber);
            String checkInMessage = library.checkOutBook(selectedBook);
            System.out.println(checkInMessage);
        }catch (InputMismatchException e){
            System.err.println(e.getMessage());
        }

    }

    private void printCheckInBook(Library library){
        try{
            int selectedNumber= readNumberFromConsole("Please enter a book number: ");
            Book selectedBook = selectLibraryBook(library, selectedNumber);
            String checkInMessage = library.checkInBook(selectedBook);
            System.out.println(checkInMessage);
        }catch (InputMismatchException e){
            System.err.println(e.getMessage());
        }

    }

    public void subMenuOptionsForBooks(Library library){
        System.out.println("Please Select an Option");
        System.out.format("%-2d%-20s%n", 1, "CheckOut a Book");
        System.out.format("%-2d%-20s%n", 2, "CheckIn a Book");
        try{
            int selectedOption = readNumberFromConsole("Please select an option: ");
            if(selectedOption==1){
                printCheckInBook(library);
            }else {
                printCheckInBook(library);
            }
        }catch (InputMismatchException e){
            System.err.println(e.getMessage());
        }
    }


    public Book selectLibraryBook(Library library, int selectedBookNumber){
        try{
            Book selectedBook= library.getLibraryBookList().get(selectedBookNumber-1);
            return selectedBook;
        }catch (IndexOutOfBoundsException e){
            throw new InputMismatchException("There is no book with that number, sorry :( Try Again!");
        }

    }
    private static int castNumberFromConsole(String rawNumber){
        try {
            return Integer.parseInt(rawNumber);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("This is not a number :(");
        }
    }
}
