package com.twu.biblioteca;

import com.twu.book.Book;
import com.twu.library.Library;
import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;
import com.twu.movie.Movie;
import com.twu.user.User;
import com.twu.user.UserType;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.*;

public class LibraryTest {
    ArrayList libraryItems;
    Library awesomeLibrary;
    Book alienBook;
    Book platoBook;
    Movie backToTheFutureMovie;
    Movie christineMovie;
    ArrayList repeatedItems;
    Library repeatedLibrary;
    Map<User, List<LibraryItem>> awesomeRecordBook;
    Map<User, List<LibraryItem>> repeatedRecordBook;
    List<User> libraryUsers;

    @Before
    public void setUp() {
        libraryItems = new ArrayList<LibraryItem>(Arrays.asList(new Book("Plato", "Republic", 1984, ItemAvailability.AVAILABLE),
                new Book("Michel Foucault", "The Order of Things", 1966, ItemAvailability.RESERVED),
                new Book("Camilo Jose Cela", "Colmena", 1950, ItemAvailability.AVAILABLE),
                new Movie("Back to the future I", "Steven Spielberg", 1985, 10, ItemAvailability.AVAILABLE),
                new Movie("Saturday Night Fever", "John Badham", 1977, 9, ItemAvailability.RESERVED),
                new Movie("Scream", "Wes Craven", 1996, 7, ItemAvailability.AVAILABLE)
        ));

        libraryUsers = new ArrayList<>(Arrays.asList(
                new User("Smithy", "sm@t.com", "Middle of nowhere", "73734435", "123-4567", "test123", UserType.CUSTOMER),
                new User("John Smith", "js@t.com", "Fake St", "634767436", "123-8910", "test456", UserType.CUSTOMER),
                new User("Jane Doe", "jd@t.com", "Papaya St", "859485488", "123-1112", "test789", UserType.CUSTOMER)
                ));

        awesomeRecordBook = new HashMap<>();
        awesomeRecordBook.put(libraryUsers.get(0), new ArrayList<>(Arrays.asList(
                new Book("Michel Foucault", "The Order of Things", 1966, ItemAvailability.RESERVED),
                new Movie("Back to the future I", "Steven Spielberg", 1985, 10, ItemAvailability.AVAILABLE)
        )));
        awesomeRecordBook.put(libraryUsers.get(1), new ArrayList<>(Arrays.asList(
                new Movie("Scream", "Wes Craven", 1996, 7, ItemAvailability.AVAILABLE)
        )));

        awesomeLibrary = new Library("Librimundi", libraryItems, awesomeRecordBook);

        alienBook = new Book("F. Vega", "What happened in Roswell?", 2098, ItemAvailability.AVAILABLE);

        platoBook = (Book) libraryItems.get(0);

        backToTheFutureMovie = new Movie("Back to the future I", "Steven Spielberg", 1985, 10, ItemAvailability.AVAILABLE);

        christineMovie = new Movie("Christine", "John Carpenter", 1983, 7, ItemAvailability.RESERVED);

        repeatedItems = new ArrayList<>(Arrays.asList(new Book("Plato", "Republic", 1984, ItemAvailability.AVAILABLE),
                new Book("Michel Foucault", "The Order of Things", 1966, ItemAvailability.RESERVED),
                new Book("Camilo Jose Cela", "Colmena", 1950, ItemAvailability.AVAILABLE),
                new Book("Camilo Jose Cela", "Colmena", 1950, ItemAvailability.AVAILABLE),
                new Book("Camilo Jose Cela", "Colmena", 1950, ItemAvailability.AVAILABLE),
                new Movie("Back to the future I", "Steven Spielberg", 1985, 10, ItemAvailability.AVAILABLE),
                new Movie("Saturday Night Fever", "John Badham", 1977, 9, ItemAvailability.RESERVED),
                new Movie("Saturday Night Fever", "John Badham", 1977, 9, ItemAvailability.RESERVED)
        ));

        repeatedRecordBook = new HashMap<>();
        repeatedRecordBook.put(libraryUsers.get(1), new ArrayList<>(Arrays.asList(
                new Book("Camilo Jose Cela", "Colmena", 1950, ItemAvailability.AVAILABLE),
                new Movie("Saturday Night Fever", "John Badham", 1977, 9, ItemAvailability.RESERVED)
        )));

        repeatedLibrary = new Library("Repeated Library", repeatedItems, repeatedRecordBook);
    }


    @Test
    public void shouldReturnTrueIfBookIsPartOfLibrary() {

        assertTrue(awesomeLibrary.isItemFromLibrary(platoBook));
    }

    @Test
    public void shouldReturnTrueIfMovieIsPartOfLibrary() {

        assertTrue(awesomeLibrary.isItemFromLibrary(backToTheFutureMovie));
    }

    @Test
    public void shouldReturnFalseIfBookIsNotPartOfLibrary() {

        assertFalse(awesomeLibrary.isItemFromLibrary(alienBook));
    }

    @Test
    public void shouldReturnFalseIfMovieIsNotPartOfLibrary() {

        assertFalse(awesomeLibrary.isItemFromLibrary(christineMovie));
    }

    @Test
    public void shouldReturnPositionForItemInLibrary() {

        int platoBookPosition = 0;

        int receivedPosition = awesomeLibrary.getItemPosition(platoBook);

        assertEquals(platoBookPosition, receivedPosition);
    }

    @Test
    public void shouldReturnPositionOfFirstBookForDuplicatedItemsInLibrary() {

        int expectedPosition = 2;

        int receivedPosition = repeatedLibrary.getItemPosition(new Book("Camilo Jose Cela", "Colmena", 1950, ItemAvailability.AVAILABLE));

        assertEquals(expectedPosition, receivedPosition);
    }

    @Test
    public void shouldReturnPositionOfFirstMovieForDuplicatedItemsInLibrary() {

        int expectedPosition = 6;

        int receivedPosition = repeatedLibrary.getItemPosition(new Movie("Saturday Night Fever", "John Badham", 1977, 9, ItemAvailability.RESERVED));

        assertEquals(expectedPosition, receivedPosition);
    }

    @Test
    public void shouldReturnNegativePositionForBookNotInLibrary() {

        int defaultErrorPosition = -1;

        int receivedPosition = awesomeLibrary.getItemPosition(alienBook);

        assertEquals(defaultErrorPosition, receivedPosition);
    }

    @Test
    public void shouldReturnNegativePositionForMovieNotInLibrary() {

        int defaultErrorPosition = -1;

        int receivedPosition = awesomeLibrary.getItemPosition(christineMovie);

        assertEquals(defaultErrorPosition, receivedPosition);
    }

    @Test
    public void shouldSetBookStatusReservedForItemInLibrary() {

        awesomeLibrary.updateItemStatusInLibrary(platoBook, ItemAvailability.RESERVED);

        assertEquals(ItemAvailability.RESERVED, awesomeLibrary.getBookList().get(0).getStatus());
    }

    @Test
    public void shouldSetMovieStatusReservedForItemInLibrary() {

        awesomeLibrary.updateItemStatusInLibrary(backToTheFutureMovie, ItemAvailability.RESERVED);

        assertEquals(ItemAvailability.RESERVED, awesomeLibrary.getMovieList().get(0).getStatus());
    }

    @Test
    public void shouldNotChangeStatusForItemNotInLibrary() {

        awesomeLibrary.updateItemStatusInLibrary(alienBook, ItemAvailability.RESERVED);
        assertEquals(ItemAvailability.AVAILABLE, alienBook.getStatus());
    }

    @Test
    public void shouldReturnTrueWhenCheckingOutIfBookIsAvailableInLibrary() {

        Book desiredBook = new Book("Camilo Jose Cela", "Colmena", 1950, ItemAvailability.AVAILABLE);

        assertTrue(awesomeLibrary.checkOutItem(desiredBook));
    }

    @Test
    public void shouldReturnFalseWhenCheckingOutIfBookIsNotAvailableInLibrary() {

        assertFalse(awesomeLibrary.checkOutItem(alienBook));
    }

    @Test
    public void shouldReturnFalseWhenCheckingOutIfBookIsNotAvailableButIsInLibrary() {

        Book desiredBook = new Book("Michel Foucault", "The Order of Things", 1966, ItemAvailability.RESERVED);

        assertFalse(awesomeLibrary.checkOutItem(desiredBook));
    }

    @Test
    public void shouldChangeItemStatusToReservedAfterSuccessfulCheckOut() {

        Movie desiredMovie =new Movie("Scream", "Wes Craven", 1996, 7, ItemAvailability.AVAILABLE);

        awesomeLibrary.checkOutItem(desiredMovie);
        desiredMovie.setStatus(ItemAvailability.RESERVED);

        assertEquals(ItemAvailability.RESERVED, awesomeLibrary.getMovieList().get(2).getStatus());

    }

    @Test
    public void shouldReturnTrueWhenCheckingInIfBookIsAvailableInLibrary() {

        Book borrowedBook = new Book("Michel Foucault", "The Order of Things", 1966, ItemAvailability.RESERVED);

        assertTrue(awesomeLibrary.checkInItem(borrowedBook));
    }

    @Test
    public void shouldReturnFalseWhenCheckingInIfBookIsNotAvailableInLibrary() {

        assertFalse(awesomeLibrary.checkInItem(alienBook));
    }

    @Test
    public void shouldChangeItemStatusToAvailableIfCheckInWasSuccessful() {

        Movie desiredMovie =new Movie("Saturday Night Fever", "John Badham", 1977, 9, ItemAvailability.RESERVED);

        awesomeLibrary.checkInItem(desiredMovie);
        desiredMovie.setStatus(ItemAvailability.AVAILABLE);

        assertEquals(ItemAvailability.AVAILABLE, awesomeLibrary.getMovieList().get(1).getStatus());
    }

    @Test
    public void shouldReturnAvailableBooks() {

        List<Object> availableBooks = new ArrayList<>();
        availableBooks.add(libraryItems.get(0));
        availableBooks.add(libraryItems.get(2));

        List<LibraryItem> returnedBooks = awesomeLibrary.getItemsByStatus(awesomeLibrary.getBookList(), ItemAvailability.AVAILABLE);

        assertEquals(availableBooks, returnedBooks);
    }

    @Test
    public void shouldReturnAvailableMovies() {

        List<Object> availableMovies = new ArrayList<>();
        availableMovies.add(libraryItems.get(3));
        availableMovies.add(libraryItems.get(5));

        List<LibraryItem> returnedMovies = awesomeLibrary.getItemsByStatus(awesomeLibrary.getMovieList(), ItemAvailability.AVAILABLE);

        assertEquals(availableMovies, returnedMovies);
    }

    @Test
    public void shouldReturnReservedBooks() {

        List<Object> borrowedBooks = new ArrayList<>();
        borrowedBooks.add(libraryItems.get(1));

        List<LibraryItem> returnedBooks = awesomeLibrary.getItemsByStatus(awesomeLibrary.getBookList(), ItemAvailability.RESERVED);

        assertEquals(borrowedBooks, returnedBooks);
    }

    @Test
    public void shouldReturnReservedMovies() {

        List<Object> borrowedMovies = new ArrayList<>();
        borrowedMovies.add(libraryItems.get(4));

        List<LibraryItem> returnedBooks = awesomeLibrary.getItemsByStatus(awesomeLibrary.getMovieList(), ItemAvailability.RESERVED);

        assertEquals(borrowedMovies, returnedBooks);
    }

    @Test
    public void shouldAddBookToUserThatAlreadyExistsInRegistry(){
        List<LibraryItem> booksCheckedOutByUser = awesomeRecordBook.get(libraryUsers.get(0));
        booksCheckedOutByUser.add((LibraryItem) libraryItems.get(5));

        awesomeLibrary.registerCheckOut(libraryUsers.get(0), new Movie("Scream", "Wes Craven", 1996, 7, ItemAvailability.AVAILABLE));
        List<LibraryItem> returnedBooksForUser = awesomeLibrary.getLibraryRegistryBook().get(libraryUsers.get(0));

        assertEquals(booksCheckedOutByUser, returnedBooksForUser);
    }

    @Test
    public void shouldAddNewRegistryToLibraryRecordForUserNotInRecord(){
        List<LibraryItem> booksCheckedOutByUser = new ArrayList<>();
        booksCheckedOutByUser.add((LibraryItem) libraryItems.get(2));

        awesomeLibrary.registerCheckOut(libraryUsers.get(2), new Book("Camilo Jose Cela", "Colmena", 1950, ItemAvailability.AVAILABLE));
        List<LibraryItem> returnedBooksForUser = awesomeLibrary.getLibraryRegistryBook().get(libraryUsers.get(2));

        assertEquals(booksCheckedOutByUser, returnedBooksForUser);
    }

}
