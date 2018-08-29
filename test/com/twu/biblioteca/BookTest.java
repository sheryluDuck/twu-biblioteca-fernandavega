package com.twu.biblioteca;


import com.twu.book.Book;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class BookTest {

    Book libritoDisponible = new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE);
    Book libritoPrestado = new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

    @Test
    public void shouldReturnBookIsAvailableWhenStatusIsAvailable()
    {
        assertTrue(libritoDisponible.isBookAvailable());
    }
    @Test
    public void shouldReturnBookIsNotAvailableWhenStatusIsDifferentFromAvailable(){

        assertFalse(libritoPrestado.isBookAvailable());
    }
    @Test
    public void shouldReturnBookStatusReservedWhenCheckingOutIfBookIsAvailable(){
        Book libritoParaReservar = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);
        libritoParaReservar.checkoutBook();
        assertEquals(Book.BookAvailabilityStatus.RESERVED, libritoParaReservar.getBookStatus());
    }
}
