package com.twu.biblioteca;


import com.twu.book.Book;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class BookTest {

    Book availableBook = new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE);
    Book borrowedBook = new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

    @Test
    public void shouldReturnBookIsAvailableWhenStatusIsAvailable()
    {
        assertTrue(availableBook.isBookAvailable());
    }
    @Test
    public void shouldReturnBookIsNotAvailableWhenStatusIsDifferentFromAvailable(){

        assertFalse(borrowedBook.isBookAvailable());
    }

}
