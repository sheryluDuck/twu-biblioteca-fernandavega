package com.twu.ui;

import com.twu.book.Book;
import com.twu.library.Library;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static junit.framework.TestCase.*;

public class UIActionsTest {
    List<Book> libraryBooks = new ArrayList<>();
    Library awesomeLibrary;

    UIActions consoleUI;

    @Before
    public void setUp(){
        consoleUI = new UIActions();
        libraryBooks.removeAll(libraryBooks);
        libraryBooks.add(new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE));
        libraryBooks.add(new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED));
        libraryBooks.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));
        awesomeLibrary = new Library("Awesome Library", libraryBooks);
    }

    @Test
    public void shouldReturnBookWhenSelectingAValidBook(){
        setUp();
        Book desiredBook = new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE);

        Book returnedBook = consoleUI.selectLibraryBook(awesomeLibrary.getLibraryBookList(), 1);

        assertEquals(desiredBook, returnedBook);

    }

    @Test(expected = InputMismatchException.class)
    public void shouldThrowExceptionWhenSelectingAnInvalidBook(){
        setUp();
        int outOfArrayBook = 5;
        consoleUI.selectLibraryBook(awesomeLibrary.getLibraryBookList(), outOfArrayBook);

    }


}
