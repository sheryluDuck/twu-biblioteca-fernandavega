package com.twu.ui;

import com.twu.book.Book;
import com.twu.library.Library;
import org.junit.Test;
import static junit.framework.TestCase.*;

public class UIActionsTest {
    Book[] libritosVarios= {
            new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE),
            new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED),
            new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE)
    };
    Library librimundi = new Library("Librimundi", libritosVarios);
    @Test
    public void shouldReturnBookWhenSelectingAValidBook(){
        UIActions primeraUI = new UIActions();
        Book elLibroQueQuiero = new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE);
        Book elLibroQueMeDieron = primeraUI.selectLibraryBook(librimundi, 1);
        assertEquals(elLibroQueQuiero, elLibroQueMeDieron);
    }
    @Test
    public void shouldThrowExceptionWhenSelectingAnInvalidBook(){

    }
}
