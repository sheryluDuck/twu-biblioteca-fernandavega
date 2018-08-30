package com.twu.library;

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

    //should be private(?)
    public int getBookPosition(Book book){
        if(isBookFromLibrary(book)){
            return Arrays.asList(this.libraryBookList).indexOf(book);
        }else{
            return -1;
        }
    }

    public void updateBookStatusInLibrary(Book book, Book.BookAvailabilityStatus bookStatus){
        int bookPosition = getBookPosition(book);
        if(bookPosition>-1){
            this.libraryBookList[bookPosition].setBookStatus(bookStatus);
        }
    }

    public String checkOutBook(Book book){

        if(book.isBookAvailable() && isBookFromLibrary(book)){
            updateBookStatusInLibrary(book, Book.BookAvailabilityStatus.RESERVED);
            return "Thank you! Enjoy the book";
        }else {
            return "That book is not available.";
        }

    }

    public String checkInBook(Book book){
        if(isBookFromLibrary(book)){
            updateBookStatusInLibrary(book, Book.BookAvailabilityStatus.AVAILABLE);
            return "Thank you for returning the book.";
        }else {
            return "That is not a valid book to return.";
        }

    }

}
