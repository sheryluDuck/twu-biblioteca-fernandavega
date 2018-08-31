package com.twu.book;

import java.util.Objects;

public class Book {
    public String author;
    public String bookName;
    public int publishYear;
    public enum BookAvailabilityStatus{
        RESERVED,
        AVAILABLE;
    }
    private BookAvailabilityStatus bookStatus;

    @Override
    public int hashCode() {
        return Objects.hash(author, bookName, publishYear);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Book)) {
            return false;
        }
        Book book = (Book) obj;
        return Objects.equals(author.toLowerCase(), book.author.toLowerCase())&&
                Objects.equals(bookName.toLowerCase(), bookName.toLowerCase())&&
                publishYear == book.publishYear;

    }

    public Book(String author, String bookName, int publishYear, BookAvailabilityStatus bookStatus) {
        this.author = author;
        this.bookName = bookName;
        this.publishYear = publishYear;
        this.bookStatus = bookStatus;
    }

    public BookAvailabilityStatus getBookStatus() {
        return bookStatus;
    }


    public void setBookStatus(BookAvailabilityStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public boolean isBookAvailable(){
        return this.getBookStatus()==BookAvailabilityStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", publishYear=" + publishYear +
                '}';
    }
}
