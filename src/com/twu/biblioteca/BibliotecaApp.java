package com.twu.biblioteca;


import com.twu.book.Book;
import com.twu.library.Library;
import com.twu.ui.UIActions;

import java.util.Arrays;

public class BibliotecaApp {

    public static void main(String[] args) {
        Book[] libraryBooks= {
                new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE),
                new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED),
                new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE),
                new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE)
        };
        Library awesomeLibrary = new Library("Awesome Library", Arrays.asList(libraryBooks));
       UIActions printer = new UIActions();
       printer.mainMenu(awesomeLibrary);


    }
}
