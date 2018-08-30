package com.twu.com.twu.library;

import com.twu.book.Book;

import java.util.Arrays;

public class Library {
    String libraryName;
    Book[] libraryBookList;

    public Library(String libraryName, Book[] libraryBookList) {
        this.libraryName = libraryName;
        this.libraryBookList = libraryBookList;
    }

    public Book[] getLibraryBookList() {
        return libraryBookList;
    }

    public boolean isBookFromLibrary(Book book){
        return Arrays.asList(this.libraryBookList).contains(book);
    }
}
