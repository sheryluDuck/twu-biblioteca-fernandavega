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
    public int readNumberFromConsole(){
        int selectedBookNumber = 0;
        System.out.println("Please enter a book number: ");
        String inputString = scanIn.nextLine();
        selectedBookNumber= castNumberFromConsole(inputString);
        return selectedBookNumber;
    }
    public Book selectLibraryBook(Library library, int selectedBookNumber){
        Book selectedBook = null;
        try{
            selectedBook= library.getLibraryBookList()[selectedBookNumber-1];
            System.out.println("Selected Book: ");
            System.out.println(selectedBook.toString());
            return selectedBook;
        }catch (ArrayIndexOutOfBoundsException e){
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
