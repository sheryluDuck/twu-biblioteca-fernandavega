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
    //Moving this to a Library Operation Class in the future :)
    public String checkoutBook(){
        if(this.isBookAvailable()){
            this.setBookStatus(BookAvailabilityStatus.RESERVED);
            return "Thank you! Enjoy the book";
        }else {
            return "That book is not available.";
        }
    }
    public String checkinBook(){
        this.setBookStatus(BookAvailabilityStatus.AVAILABLE);
        return "Thank you for returning the book.";
    }
}
