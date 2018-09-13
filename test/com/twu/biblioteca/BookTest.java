package com.twu.biblioteca;


import com.twu.book.Book;
import com.twu.libraryItem.ItemAvailability;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class BookTest {

    Book availableBook;
    Book borrowedBook;

    @Before
    public void setUpd(){
        availableBook = new Book("Plato", "Republic", 1984, ItemAvailability.AVAILABLE);
        borrowedBook = new Book("Michel Foucault", "The Order of Things", 1966, ItemAvailability.RESERVED);
    }

    @Test
    public void shouldReturnBookIsAvailableWhenStatusIsAvailable()
    {
        assertTrue(availableBook.isAvailable());
    }
    @Test
    public void shouldReturnBookIsNotAvailableWhenStatusIsDifferentFromAvailable(){
        assertFalse(borrowedBook.isAvailable());
    }
    @Test
    public void shouldChangeStatusOfBook(){
        availableBook.setStatus(ItemAvailability.RESERVED);

        assertEquals(ItemAvailability.RESERVED, availableBook.getStatus());
    }

    @Test
    public void shouldReturnBookStatus(){

        assertEquals(ItemAvailability.RESERVED, borrowedBook.getStatus());
    }
}
