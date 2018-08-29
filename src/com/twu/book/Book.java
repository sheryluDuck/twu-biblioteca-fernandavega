package com.twu.book;

public class Book {
    String author;
    String bookName;
    int publishYear;
    public enum BookAvailabilityStatus{
        RESERVED,
        AVAILABLE;
    }
    private BookAvailabilityStatus bookStatus;

    public BookAvailabilityStatus getBookStatus() {
        return bookStatus;
    }

    public Book(String author, String bookName, int publishYear, BookAvailabilityStatus bookStatus) {
        this.author = author;
        this.bookName = bookName;
        this.publishYear = publishYear;
        this.bookStatus = bookStatus;
    }

    private void setBookStatus(BookAvailabilityStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public boolean isBookAvailable(){
        return this.getBookStatus()==BookAvailabilityStatus.AVAILABLE;
    }
    public void checkoutBook(){
        this.setBookStatus(BookAvailabilityStatus.RESERVED);
    }
}
