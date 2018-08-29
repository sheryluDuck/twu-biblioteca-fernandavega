package com.twu.biblioteca;


import com.twu.book.Book;
import org.junit.Assert;
import org.junit.Test;

public class BookTest {

    @Test
    public void shouldReturnBookIsAvailableWhenStatusIsAvailable()
    {
        Book librito = new Book();
        librito.setBookStatus(Book.BookAvailabilityStatus.AVAILABLE);
        Assert.assertTrue(librito.isBookAvailable());
    }
    @Test
    public void shouldReturnBookIsNotAvailableWhenStatusIsDifferentFromAvailable(){
        Book librito = new Book();
        librito.setBookStatus(Book.BookAvailabilityStatus.RESERVED);
        Assert.assertFalse(librito.isBookAvailable());
    }
}
