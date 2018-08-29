package com.twu.book;

public class Book {
    String author;
    int publishYear;
    public enum BookAvailabilityStatus{
        RESERVED,
        AVAILABLE;
    }
    private BookAvailabilityStatus bookStatus;

    public BookAvailabilityStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookAvailabilityStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public boolean isBookAvailable(){
        return true;
    }
}
