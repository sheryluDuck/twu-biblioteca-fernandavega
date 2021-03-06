package com.twu.ui;

import com.twu.book.Book;
import com.twu.library.Library;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UIActions {
    static final Scanner scanIn = new Scanner(System.in);

    public void printLibraryBooksByStatus(Library library, Book.BookAvailabilityStatus bookStatus){

        List<Book> bookList = library.getBooksByStatus(bookStatus);

        if(!isListOfBooksNotEmpty(bookList)){
            System.out.println("Sorry, there are no books :(");
        }else{
            System.out.format("|%-2s|%-20s|%-50s|%-5s|%n", "N", "Author", "Book", "Year");
            int counter = 0;
            for (Book book:
                    bookList) {
                counter++;
                System.out.format("|%-2d|%-20s|%-50s|%-5d|%n",counter, book.author, book.bookName, book.publishYear);
            }
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
            Book selectedBook = selectLibraryBook(library.getLibraryBookList(), selectedNumber);
            System.out.println("Selected Book: ");
            System.out.println(selectedBook.toString());
        }catch (InputMismatchException e){
            System.err.println(e.getMessage());
        }

    }

    private boolean isListOfBooksNotEmpty(List<Book> bookList){
        return bookList.size()>0;
    }

    private void printCheckOutBook(Library library){
        try{
            int selectedNumber = readNumberFromConsole("Please enter a book number: ");
            Book selectedBook = selectLibraryBook(library.getBooksByStatus(Book.BookAvailabilityStatus.AVAILABLE), selectedNumber);
            String checkInMessage = library.checkOutBook(selectedBook);
            System.out.println(checkInMessage);
        }catch (InputMismatchException e){
            System.err.println(e.getMessage());
        }

    }

    private void printCheckInBook(Library library){
        if(isListOfBooksNotEmpty(library.getBooksByStatus(Book.BookAvailabilityStatus.RESERVED))) {
            System.out.println("This is a list of books you can return:");
            printLibraryBooksByStatus(library, Book.BookAvailabilityStatus.RESERVED);
            try{
                int selectedNumber= readNumberFromConsole("Please enter a book number: ");
                Book selectedBook = selectLibraryBook(library.getBooksByStatus(Book.BookAvailabilityStatus.RESERVED), selectedNumber);
                String checkInMessage = library.checkInBook(selectedBook);
                System.out.println(checkInMessage);
            }catch (InputMismatchException e){
                System.err.println(e.getMessage());
            }
        }else {
            printLibraryBooksByStatus(library, Book.BookAvailabilityStatus.RESERVED);
        }

    }

    public void subMenuOptionsForBooks(Library library){
        try{
            System.out.println("This is a list of available books:");
            printLibraryBooksByStatus(library, Book.BookAvailabilityStatus.AVAILABLE);
            System.out.println("Please Select an Option");
            System.out.format("%-2d%-20s%n", 1, "CheckOut a Book");
            System.out.format("%-2d%-20s%n", 2, "CheckIn a Book");
            System.out.format("%-2d%-20s%n", 3, "Exit :(");
            int selectedOption = readNumberFromConsole("Please select an option: ");
            //This could be improved
            switch (selectedOption){
                case 1:
                    if(isListOfBooksNotEmpty(library.getBooksByStatus(Book.BookAvailabilityStatus.AVAILABLE))){
                        printCheckOutBook(library);
                    }
                    subMenuOptionsForBooks(library);
                    break;
                case 2:
                    printCheckInBook(library);
                    subMenuOptionsForBooks(library);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Select a valid option!");
                    subMenuOptionsForBooks(library);
            }
        }catch (InputMismatchException e){
            System.err.println(e.getMessage());
            subMenuOptionsForBooks(library);
        }
    }

    public void mainMenu(Library library){
        System.out.println("Welcome to Biblioteca :)");
        try{
            System.out.println("Please Select an Option");
            System.out.format("%-2d%-20s%n", 1, "List of available books");
            System.out.format("%-2d%-20s%n", 2, "Exit :(");
            int selectedOption = readNumberFromConsole("Please select an option: ");
            switch (selectedOption){
                case 1:
                    subMenuOptionsForBooks(library);
                    break;
                case 2:
                    break;
                default:
                        System.out.println("Select a valid option!");
                        mainMenu(library);
            }
        }catch (InputMismatchException e){
            System.err.println(e.getMessage());
            mainMenu(library);
        }
    }

    public Book selectLibraryBook(List<Book> bookList, int selectedBookNumber){
        try{
            Book selectedBook= bookList.get(selectedBookNumber-1);
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
