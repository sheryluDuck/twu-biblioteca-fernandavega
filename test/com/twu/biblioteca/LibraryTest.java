package com.twu.biblioteca;

import com.twu.book.Book;
import com.twu.library.Library;
import org.junit.After;
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
        libritosVarios.add(new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE));
        libritosVarios.add(new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED));
        libritosVarios.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));

        librimundi = new Library("Librimundi", libritosVarios);

        libroExtraterrestre = new Book("F. Vega", "What happened in Rosswell?", 2098, Book.BookAvailabilityStatus.AVAILABLE);
        libroDePlaton = libritosVarios.get(0);

    }

    @After
    public void tearDown(){
        libritosVarios.removeAll(libritosVarios);

    }

    @Test
    public void shouldReturnTrueIfBookIsPartOfLibrary(){


        assertTrue(librimundi.isBookFromLibrary(libroDePlaton));

    }

    @Test
    public void shouldReturnFalseIfBookIsNotPartOfLibrary(){

        assertFalse(librimundi.isBookFromLibrary(libroExtraterrestre));
    }

    @Test
    public void shouldReturnPositionForBookInLibrary() {

        int platoBookPosition = 0;

        int receivedPosition = librimundi.getBookPosition(libroDePlaton);

        assertEquals(platoBookPosition, receivedPosition);
    }
    /*
    @Test
    public void shouldReturnPositionOfFirstBookForDuplicatedBooksInLibrary() {

        List<Book> libritosRepetidos = new ArrayList<>();
        libritosRepetidos.add(new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE));
        libritosRepetidos.add(new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED));
        libritosRepetidos.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));
        libritosRepetidos.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));
        libritosRepetidos.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));

        Library libreriaConRepetidos = new Library("Repollo", libritosRepetidos);

        int posicionEsperada= 2;

        int receivedPosition= libreriaConRepetidos.getBookPosition(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));

        assertEquals(posicionEsperada, receivedPosition);
    }
    */

    @Test
    public void shouldReturnNegativePositionForBookNotInLibrary() {

        int defaultErrorPosition = -1;

        int receivedPosition = librimundi.getBookPosition(libroExtraterrestre);

        assertEquals(defaultErrorPosition, receivedPosition);
    }

    @Test
    public void shouldSetBookStatusReservedForBookInLibrary(){


        librimundi.updateBookStatusInLibrary(libroDePlaton, Book.BookAvailabilityStatus.RESERVED);

        assertEquals(Book.BookAvailabilityStatus.RESERVED, librimundi.getLibraryBookList().get(0).getBookStatus());
    }
    @Test
    public void shouldNotChangeStatusForBookNotInLibrary(){

        librimundi.updateBookStatusInLibrary(libroExtraterrestre, Book.BookAvailabilityStatus.RESERVED);
        assertEquals(Book.BookAvailabilityStatus.AVAILABLE, libroExtraterrestre.getBookStatus());
    }

    @Test
    public void shouldReturnSuccessMessageWhenCheckingOutIfBookIsAvailableInLibrary(){

        Book libritoQueQuiero = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);

        String receivedMessage = librimundi.checkOutBook(libritoQueQuiero);

        assertEquals("Thank you! Enjoy the book", receivedMessage);
    }

    @Test
    public void shouldReturnErrorMessageWhenCheckingOutIfBookIsNotAvailableInLibrary(){


        String receivedMessage = librimundi.checkOutBook(libroExtraterrestre);

        assertEquals("That book is not available.", receivedMessage);
    }

    @Test
    public void shouldReturnErrorMessageWhenCheckingOutIfBookIsNotAvailableButIsInLibrary(){

        Book libritoQueQuiero = new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        String receivedMessage = librimundi.checkOutBook(libritoQueQuiero);

        assertEquals("That book is not available.", receivedMessage);
    }

    @Test
    public void shouldReturnBookStatusReservedAfterSuccessfulCheckOut(){

        Book libritoQueQuiero = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);

        librimundi.checkOutBook(libritoQueQuiero);

        assertEquals(Book.BookAvailabilityStatus.RESERVED, librimundi.getLibraryBookList().get(librimundi.getBookPosition(libritoQueQuiero)).getBookStatus());

    }

    @Test

    public void shouldReturnSuccessMessageWhenCheckingInIfBookIsAvailableInLibrary(){

        Book libroParaDevolver= new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        String receivedMessage= librimundi.checkInBook(libroParaDevolver);

        assertEquals("Thank you for returning the book.", receivedMessage);
    }

    @Test

    public void shouldReturnErrorMessageWhenCheckingInIfBookIsNotAvailableInLibrary(){


        String receivedMessage= librimundi.checkInBook(libroExtraterrestre);

        assertEquals("That is not a valid book to return.", receivedMessage);
    }

    @Test

    public void shouldChangeBookStatusIfCheckInWasSuccessfull(){

        Book libroParaDevolver= new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        librimundi.checkInBook(libroParaDevolver);

        assertEquals(Book.BookAvailabilityStatus.AVAILABLE, librimundi.getLibraryBookList().get(librimundi.getBookPosition(libroParaDevolver)).getBookStatus());
    }

    @Test

    public void shouldReturnAvailableBooks(){

        List <Book> libritosDisponibles = new ArrayList<>();
        libritosDisponibles.add(libritosVarios.get(0));
        libritosDisponibles.add(libritosVarios.get(2));

        List <Book> libritosRetornados = librimundi.getBooksByStatus(Book.BookAvailabilityStatus.AVAILABLE);

        assertEquals(libritosDisponibles, libritosRetornados);
    }

    @Test

    public void shouldReturnReservedBooks(){

        List <Book> libritosReservados = new ArrayList<>();
        libritosReservados.add(libritosVarios.get(1));

        List <Book> libritosRetornados = librimundi.getBooksByStatus(Book.BookAvailabilityStatus.RESERVED);

        assertEquals(libritosReservados, libritosRetornados);
    }

    @Test

    public void shouldReturnEmptyListIfNoAvailableBooks(){
        List <Book> librosReservados = new ArrayList<>();
        librosReservados.add(new Book("Lord Byron", "Don Juan", 1973, Book.BookAvailabilityStatus.RESERVED));
        librosReservados.add(new Book("Anonimo", "Celestina", 1965, Book.BookAvailabilityStatus.RESERVED));
        Library bibliotecaReservada= new Library("Sunshine", librosReservados);

        List <Book> libritosRetornados = bibliotecaReservada.getBooksByStatus(Book.BookAvailabilityStatus.AVAILABLE);

        assertEquals(new ArrayList<>(), libritosRetornados);
    }
}
