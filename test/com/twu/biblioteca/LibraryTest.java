package com.twu.biblioteca;

import com.twu.book.Book;
import com.twu.com.twu.library.Library;
import org.junit.Test;
import static junit.framework.TestCase.*;

public class LibraryTest {
    Book[] libritosVarios= {
       new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE),
       new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED)
    };
    Library librimundi = new Library("Librimundi", libritosVarios);
    @Test
    public void shouldReturnTrueIfBookIsPartOfLibrary(){
        Book libroDePlaton= libritosVarios[0];
        assertTrue(librimundi.isBookFromLibrary(libroDePlaton));
    }

    @Test
    public void shouldReturnFalseIfBookIsNotPartOfLibrary(){
        Book libroExtraterrestre = new Book("F. Vega", "What happened in Rosswell?", 2098, Book.BookAvailabilityStatus.AVAILABLE);
        assertFalse(librimundi.isBookFromLibrary(libroExtraterrestre));
    }

}
