package com.twu.biblioteca;

import com.twu.book.Book;
import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class LibraryTest {
    List<Book> libritosVarios = new ArrayList<>();
    Library librimundi;
    Book libroExtraterrestre;
    Book libroDePlaton;

    @Before
    public void setUp(){
        libritosVarios.removeAll(libritosVarios);
        libritosVarios.add(new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE));
        libritosVarios.add(new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED));
        libritosVarios.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));

        librimundi = new Library("Librimundi", libritosVarios);

        libroExtraterrestre = new Book("F. Vega", "What happened in Rosswell?", 2098, Book.BookAvailabilityStatus.AVAILABLE);
        libroDePlaton = libritosVarios.get(0);
    }

    @Test
    public void shouldReturnTrueIfBookIsPartOfLibrary(){
        setUp();

        assertTrue(librimundi.isBookFromLibrary(libroDePlaton));
    }

    @Test
    public void shouldReturnFalseIfBookIsNotPartOfLibrary(){
        setUp();

        assertFalse(librimundi.isBookFromLibrary(libroExtraterrestre));
    }

    @Test
    public void shouldReturnPositionForBookInLibrary() {
        setUp();
        int platoBookPosition = 0;

        int receivedPosition = librimundi.getBookPosition(libroDePlaton);

        assertEquals(platoBookPosition, receivedPosition);
    }

    @Test
    public void shouldReturnNegativePositionForBookNotInLibrary() {
        setUp();
        int defaultErrorPosition = -1;

        int receivedPosition = librimundi.getBookPosition(libroExtraterrestre);

        assertEquals(defaultErrorPosition, receivedPosition);
    }

    @Test
    public void shouldSetBookStatusReservedForBookInLibrary(){
        setUp();

        librimundi.updateBookStatusInLibrary(libroDePlaton, Book.BookAvailabilityStatus.RESERVED);

        assertEquals(Book.BookAvailabilityStatus.RESERVED, librimundi.getLibraryBookList().get(0).getBookStatus());
    }
    @Test
    public void shouldNotChangeStatusForBookNotInLibrary(){
        setUp();
        librimundi.updateBookStatusInLibrary(libroExtraterrestre, Book.BookAvailabilityStatus.RESERVED);
        assertEquals(Book.BookAvailabilityStatus.AVAILABLE, libroExtraterrestre.getBookStatus());
    }

    @Test
    public void shouldReturnSuccessMessageWhenCheckingOutIfBookIsAvailableInLibrary(){
        setUp();
        Book libritoQueQuiero = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);

        String receivedMessage = librimundi.checkOutBook(libritoQueQuiero);

        assertEquals("Thank you! Enjoy the book", receivedMessage);
    }

    @Test
    public void shouldReturnErrorMessageWhenCheckingOutIfBookIsNotAvailableInLibrary(){
        setUp();

        String receivedMessage = librimundi.checkOutBook(libroExtraterrestre);

        assertEquals("That book is not available.", receivedMessage);
    }

    @Test
    public void shouldReturnErrorMessageWhenCheckingOutIfBookIsNotAvailableButIsInLibrary(){
        setUp();
        Book libritoQueQuiero = new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        String receivedMessage = librimundi.checkOutBook(libritoQueQuiero);

        assertEquals("That book is not available.", receivedMessage);
    }

    @Test
    public void shouldReturnBookStatusReservedAfterSuccessfulCheckOut(){
        setUp();
        Book libritoQueQuiero = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);

        librimundi.checkOutBook(libritoQueQuiero);

        assertEquals(Book.BookAvailabilityStatus.RESERVED, librimundi.getLibraryBookList().get(librimundi.getBookPosition(libritoQueQuiero)).getBookStatus());

    }

    @Test

    public void shouldReturnSuccessMessageWhenCheckingInIfBookIsAvailableInLibrary(){
        setUp();
        Book libroParaDevolver= new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        String receivedMessage= librimundi.checkInBook(libroParaDevolver);

        assertEquals("Thank you for returning the book.", receivedMessage);
    }

    @Test

    public void shouldReturnErrorMessageWhenCheckingInIfBookIsNotAvailableInLibrary(){
        setUp();

        String receivedMessage= librimundi.checkInBook(libroExtraterrestre);

        assertEquals("That is not a valid book to return.", receivedMessage);
    }

    @Test

    public void shouldChangeBookStatusIfCheckInWasSuccessfull(){
        setUp();
        Book libroParaDevolver= new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        librimundi.checkInBook(libroParaDevolver);

        assertEquals(Book.BookAvailabilityStatus.AVAILABLE, librimundi.getLibraryBookList().get(librimundi.getBookPosition(libroParaDevolver)).getBookStatus());
    }
    /*
    @Test

    public void shouldReturnAvailableBooks(){
        setUp();
        Book[] libritosDisponibles = new Book[] {libritosVarios[0], libritosVarios[2]};

        Book[] libritosRetornados = librimundi.getAvailableBooks();

        Assert.assertArrayEquals(libritosDisponibles, libritosRetornados);
    }*/
}
