package com.twu.book;

import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;

import java.util.Objects;

public class Book implements LibraryItem {
    public String author;
    public String bookName;
    public int publishYear;
    private ItemAvailability bookAvailabilityStatus;

    @Override
    public ItemAvailability getStatus() {
        return this.bookAvailabilityStatus;
    }

    @Override
    public void setStatus(ItemAvailability itemAvailability) {
        this.bookAvailabilityStatus = itemAvailability;
    }

    @Override
    public boolean isAvailable() {
        return this.bookAvailabilityStatus.equals(ItemAvailability.AVAILABLE);
    }

    @Override
    public String getItemName() {
        return this.bookName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, bookName, publishYear, bookAvailabilityStatus);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Book)) {
            return false;
        }
        Book book = (Book) obj;
        return author.toLowerCase().equals(book.author.toLowerCase())&&
                bookName.toLowerCase().equals(book.bookName.toLowerCase())&&
                publishYear == book.publishYear &&
                bookAvailabilityStatus.equals(book.bookAvailabilityStatus);
    }

    @Override
    public String toString() {
        return String.format("|%-20s|%-50s|%-5d|", this.author, this.bookName, this.publishYear);
    }

    public Book(String author, String bookName, int publishYear, ItemAvailability itemAvailability) {
        this.author = author;
        this.bookName = bookName;
        this.publishYear = publishYear;
        this.bookAvailabilityStatus = itemAvailability;
    }

}
