package com.twu.biblioteca;

import com.twu.book.Book;
import com.twu.library.Library;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class LibraryTest {
    Book[] libritosVarios= {
       new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE),
       new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED),
       new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE)
    };
    Library librimundi = new Library("Librimundi", libritosVarios);
    Book libroExtraterrestre = new Book("F. Vega", "What happened in Rosswell?", 2098, Book.BookAvailabilityStatus.AVAILABLE);
    @Test
    public void shouldReturnTrueIfBookIsPartOfLibrary(){
        Book libroDePlaton= new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE);
        assertTrue(librimundi.isBookFromLibrary(libroDePlaton));
    }

    @Test
    public void shouldReturnFalseIfBookIsNotPartOfLibrary(){
        assertFalse(librimundi.isBookFromLibrary(libroExtraterrestre));
    }

    @Test
    public void shouldReturnPositionForBookInLibrary() {
        int platoBookPosition = 0;
        Book platoBook = new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE);
        int receivedPosition = librimundi.getBookPosition(platoBook);
        assertEquals(platoBookPosition, receivedPosition);
    }

    @Test
    public void shouldReturnNegativePositionForBookNotInLibrary() {
        int defaultErrorPosition = -1;
        int receivedPosition = librimundi.getBookPosition(libroExtraterrestre);
        assertEquals(defaultErrorPosition, receivedPosition);
    }

    @Test
    public void shouldSetBookStatusReservedForBookInLibrary(){
        Library cornerLibrary = new Library("Corner", libritosVarios);
        cornerLibrary.updateBookStatusInLibrary(libritosVarios[0], Book.BookAvailabilityStatus.RESERVED);
        assertEquals(Book.BookAvailabilityStatus.RESERVED, cornerLibrary.getLibraryBookList()[0].getBookStatus());
    }
    @Test
    public void shouldNotChangeStatusForBookNotInLibrary(){
        Library cornerLibrary = new Library("Corner", libritosVarios);
        cornerLibrary.updateBookStatusInLibrary(libroExtraterrestre, Book.BookAvailabilityStatus.RESERVED);
        assertEquals(Book.BookAvailabilityStatus.AVAILABLE, libroExtraterrestre.getBookStatus());
    }

    @Test
    public void shouldReturnSuccessMessageWhenCheckingOutIfBookIsAvailableInLibrary(){
        Library cornerLibrary = new Library("Corner", libritosVarios);
        Book libritoQueQuiero = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);
        String receivedMessage = cornerLibrary.checkOutBook(libritoQueQuiero);
        assertEquals("Thank you! Enjoy the book", receivedMessage);
    }

    @Test
    public void shouldReturnErrorMessageWhenCheckingOutIfBookIsNotAvailableInLibrary(){
        Library cornerLibrary = new Library("Corner", libritosVarios);

        String receivedMessage = cornerLibrary.checkOutBook(libroExtraterrestre);

        assertEquals("That book is not available.", receivedMessage);
    }

    @Test
    public void shouldReturnErrorMessageWhenCheckingOutIfBookIsNotAvailableButIsInLibrary(){
        Library cornerLibrary = new Library("Corner", libritosVarios);
        Book libritoQueQuiero = new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        String receivedMessage = cornerLibrary.checkOutBook(libritoQueQuiero);

        assertEquals("That book is not available.", receivedMessage);
    }

    @Test
    public void shouldReturnBookStatusReservedAfterSuccessfullCheckOut(){
        Library cornerLibrary = new Library("Corner", libritosVarios);
        Book libritoQueQuiero = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);

        cornerLibrary.checkOutBook(libritoQueQuiero);

        assertEquals(Book.BookAvailabilityStatus.RESERVED, cornerLibrary.getLibraryBookList()[cornerLibrary.getBookPosition(libritoQueQuiero)].getBookStatus());

    }
}
