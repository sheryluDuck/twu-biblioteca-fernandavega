package com.twu.ui;

import com.twu.book.Book;
import com.twu.library.Library;
import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

import static junit.framework.TestCase.*;

public class UIActionsTest {
    Book[] libritosVarios;
    Library librimundi;

    UIActions primeraUI;

    @Before
    public void setUp(){
        primeraUI = new UIActions();
        libritosVarios = new Book[]{
                new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE),
                new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED),
                new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE)
        };
        librimundi= new Library("Librimundi", libritosVarios);
    }
    @Test
    public void shouldReturnBookWhenSelectingAValidBook(){
        setUp();
        Book elLibroQueQuiero = new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE);

        Book elLibroQueMeDieron = primeraUI.selectLibraryBook(librimundi, 1);

        assertEquals(elLibroQueQuiero, elLibroQueMeDieron);
    }
    @Test(expected = InputMismatchException.class)
    public void shouldThrowExceptionWhenSelectingAnInvalidBook(){
        setUp();
        int outOfArrayBook = 4;

        primeraUI.selectLibraryBook(librimundi, outOfArrayBook);
    }


}
