package com.twu.operation;

import com.twu.book.Book;
import com.twu.library.Library;

public class LibraryOperation {
    public String checkOutBook(Library library, Book book){

        if(book.isBookAvailable() && library.isBookFromLibrary(book)){
            library.updateBookStatusInLibrary(book, Book.BookAvailabilityStatus.RESERVED);
            book.setBookStatus(Book.BookAvailabilityStatus.RESERVED);
            return "Thank you! Enjoy the book";
        }else {
            return "That book is not available.";
        }

    }
}
