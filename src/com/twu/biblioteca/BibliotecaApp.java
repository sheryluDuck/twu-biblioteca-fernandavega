package com.twu.biblioteca;


import com.twu.book.Book;
import com.twu.library.Library;
import com.twu.ui.UIActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        Book[] libritosVarios= {
                new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE),
                new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED),
                new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE)
        };
        Library librimundi = new Library("Librimundi", Arrays.asList(libritosVarios));
        System.out.println(librimundi.getLibraryBookList().size());
        UIActions printer = new UIActions();
        printer.printLibraryBooks(librimundi);
        printer.subMenuOptionsForBooks(librimundi);


    }
}
