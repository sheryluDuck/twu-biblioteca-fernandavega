package com.twu.biblioteca;

import com.twu.book.Book;
import com.twu.library.Library;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class LibraryTest {
    List<Book> libraryBooks = new ArrayList<>();
    Library awesomeLibrary;
    Book alienBook;
    Book platoBook;

    @Before
    public void setUp(){
        libraryBooks.add(new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE));
        libraryBooks.add(new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED));
        libraryBooks.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));

        awesomeLibrary = new Library("Librimundi", libraryBooks);

        alienBook = new Book("F. Vega", "What happened in Rosswell?", 2098, Book.BookAvailabilityStatus.AVAILABLE);
        platoBook = libraryBooks.get(0);

    }

    @After
    public void tearDown(){
        libraryBooks.removeAll(libraryBooks);
    }

    @Test
    public void shouldReturnTrueIfBookIsPartOfLibrary(){


        assertTrue(awesomeLibrary.isBookFromLibrary(platoBook));

    }

    @Test
    public void shouldReturnFalseIfBookIsNotPartOfLibrary(){

        assertFalse(awesomeLibrary.isBookFromLibrary(alienBook));
    }

    @Test
    public void shouldReturnPositionForBookInLibrary() {

        int platoBookPosition = 0;

        int receivedPosition = awesomeLibrary.getBookPosition(platoBook);

        assertEquals(platoBookPosition, receivedPosition);
    }
    @Test
    public void shouldReturnPositionOfFirstBookForDuplicatedBooksInLibrary() {

        List<Book> repeatedBooks = new ArrayList<>();
        repeatedBooks.add(new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE));
        repeatedBooks.add(new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED));
        repeatedBooks.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));
        repeatedBooks.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));
        repeatedBooks.add(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));

        Library repeatedBooksLibrary = new Library("Re-Repeated Books", repeatedBooks);

        int expectedPosition= 2;

        int receivedPosition= repeatedBooksLibrary.getBookPosition(new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE));

        assertEquals(expectedPosition, receivedPosition);
    }

    @Test
    public void shouldReturnNegativePositionForBookNotInLibrary() {

        int defaultErrorPosition = -1;

        int receivedPosition = awesomeLibrary.getBookPosition(alienBook);

        assertEquals(defaultErrorPosition, receivedPosition);
    }

    @Test
    public void shouldSetBookStatusReservedForBookInLibrary(){


        awesomeLibrary.updateBookStatusInLibrary(platoBook, Book.BookAvailabilityStatus.RESERVED);

        assertEquals(Book.BookAvailabilityStatus.RESERVED, awesomeLibrary.getLibraryBookList().get(0).getBookStatus());
    }
    @Test
    public void shouldNotChangeStatusForBookNotInLibrary(){

        awesomeLibrary.updateBookStatusInLibrary(alienBook, Book.BookAvailabilityStatus.RESERVED);
        assertEquals(Book.BookAvailabilityStatus.AVAILABLE, alienBook.getBookStatus());
    }

    @Test
    public void shouldReturnSuccessMessageWhenCheckingOutIfBookIsAvailableInLibrary(){

        Book desiredBook = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);

        String receivedMessage = awesomeLibrary.checkOutBook(desiredBook);

        assertEquals("Thank you! Enjoy the book", receivedMessage);
    }

    @Test
    public void shouldReturnErrorMessageWhenCheckingOutIfBookIsNotAvailableInLibrary(){


        String receivedMessage = awesomeLibrary.checkOutBook(alienBook);

        assertEquals("That book is not available.", receivedMessage);
    }

    @Test
    public void shouldReturnErrorMessageWhenCheckingOutIfBookIsNotAvailableButIsInLibrary(){

        Book desiredBook = new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        String receivedMessage = awesomeLibrary.checkOutBook(desiredBook);

        assertEquals("That book is not available.", receivedMessage);
    }

    @Test
    public void shouldReturnBookStatusReservedAfterSuccessfulCheckOut(){

        Book desiredBook = new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE);

        awesomeLibrary.checkOutBook(desiredBook);
        desiredBook.setBookStatus(Book.BookAvailabilityStatus.RESERVED);

        assertEquals(Book.BookAvailabilityStatus.RESERVED, awesomeLibrary.getLibraryBookList().get(awesomeLibrary.getBookPosition(desiredBook)).getBookStatus());

    }

    @Test

    public void shouldReturnSuccessMessageWhenCheckingInIfBookIsAvailableInLibrary(){

        Book borrowedBook= new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        String receivedMessage= awesomeLibrary.checkInBook(borrowedBook);

        assertEquals("Thank you for returning the book.", receivedMessage);
    }

    @Test

    public void shouldReturnErrorMessageWhenCheckingInIfBookIsNotAvailableInLibrary(){


        String receivedMessage= awesomeLibrary.checkInBook(alienBook);

        assertEquals("That is not a valid book to return.", receivedMessage);
    }

    @Test

    public void shouldChangeBookStatusIfCheckInWasSuccessfull(){

        Book borrowedBook= new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED);

        awesomeLibrary.checkInBook(borrowedBook);
        borrowedBook.setBookStatus(Book.BookAvailabilityStatus.AVAILABLE);

        assertEquals(Book.BookAvailabilityStatus.AVAILABLE, awesomeLibrary.getLibraryBookList().get(awesomeLibrary.getBookPosition(borrowedBook)).getBookStatus());
    }

    @Test

    public void shouldReturnAvailableBooks(){

        List <Book> availableBooks = new ArrayList<>();
        availableBooks.add(libraryBooks.get(0));
        availableBooks.add(libraryBooks.get(2));

        List <Book> returnedBooks = awesomeLibrary.getBooksByStatus(Book.BookAvailabilityStatus.AVAILABLE);

        assertEquals(availableBooks, returnedBooks);
    }

    @Test

    public void shouldReturnReservedBooks(){

        List <Book> borrowedBooks = new ArrayList<>();
        borrowedBooks.add(libraryBooks.get(1));

        List <Book> returnedBooks = awesomeLibrary.getBooksByStatus(Book.BookAvailabilityStatus.RESERVED);

        assertEquals(borrowedBooks, returnedBooks);
    }

    @Test

    public void shouldReturnEmptyListIfNoAvailableBooks(){
        List <Book> borrowedBooks = new ArrayList<>();
        borrowedBooks.add(new Book("Lord Byron", "Don Juan", 1973, Book.BookAvailabilityStatus.RESERVED));
        borrowedBooks.add(new Book("Anonymous", "Celestina", 1965, Book.BookAvailabilityStatus.RESERVED));
        Library allBorrowedBooksLibrary= new Library("Sunshine", borrowedBooks);

        List <Book> returnedBooks = allBorrowedBooksLibrary.getBooksByStatus(Book.BookAvailabilityStatus.AVAILABLE);

        assertEquals(new ArrayList<>(), returnedBooks);
    }
}
