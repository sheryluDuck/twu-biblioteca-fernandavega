package com.twu.biblioteca;


import com.twu.book.Book;
import org.junit.Before;
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
    public void shouldReturnSuccessMessageWhenCheckingOutIfBookIsAvailable(){
        Book libritoParaReservar = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);
        String successMessage = libritoParaReservar.checkoutBook();
        assertEquals("Thank you! Enjoy the book", successMessage);
    }
    @Test
    public void shouldReturnBookStatusReservedWhenCheckingOutIfBookIsAvailable(){
        Book libritoParaReservar = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);
        libritoParaReservar.checkoutBook();
        assertEquals(Book.BookAvailabilityStatus.RESERVED, libritoParaReservar.getBookStatus());
    }

    @Test
    public void shouldReturnErrorMessageWhenCheckingOutIfBookIsNotAvailable(){
        assertEquals("That book is not available.", libritoPrestado.checkoutBook());
    }

    @Test
    public void shouldReturnSuccessMessageWhenCheckingIn(){
        Book libritoParaDevolver = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.RESERVED);
        String successMessage = libritoParaDevolver.checkinBook();
        assertEquals("Thank you for returning the book.", successMessage);
    }

    @Test
    public void shouldReturnBookStatusAvailableWhenCheckingIn(){
        Book libritoParaDevolver = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.RESERVED);
        libritoParaDevolver.checkinBook();
        assertEquals(Book.BookAvailabilityStatus.AVAILABLE, libritoParaDevolver.getBookStatus());
    }

}
