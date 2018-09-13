package com.twu.library;

import com.twu.book.Book;
import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;
import com.twu.movie.Movie;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    String libraryName;
    private List<LibraryItem> libraryItems;


    public Library(String libraryName, List<LibraryItem> libraryItems) {
        this.libraryName = libraryName;
        this.libraryItems = libraryItems;
    }

    public List<LibraryItem> getBookList() {
        List<LibraryItem> bookList = libraryItems.stream()
                .filter(item -> item instanceof Book)
                .collect(Collectors.toList());
        return bookList;
    }

    public List<LibraryItem> getMovieList() {
        List<LibraryItem> movieList = libraryItems.stream()
                .filter(item -> item instanceof Movie)
                .collect(Collectors.toList());
        return movieList;
    }

    public boolean isItemFromLibrary(LibraryItem libraryItem){
      return this.libraryItems.contains(libraryItem);
    }


    public int getItemPosition(LibraryItem libraryItem){
        if(isItemFromLibrary(libraryItem)){
            return this.libraryItems.indexOf(libraryItem);
        }else {
            return -1;
        }
    }

    //should be private(?)

    public void updateItemStatusInLibrary(LibraryItem libraryItem, ItemAvailability itemAvailability){
        int itemPosition = this.getItemPosition(libraryItem);
        if(itemPosition > -1){
            this.libraryItems.get(itemPosition).setStatus(itemAvailability);
        }
    }

    public boolean checkOutItem(LibraryItem libraryItem){
        if(libraryItem.isAvailable() && isItemFromLibrary(libraryItem)){
            updateItemStatusInLibrary(libraryItem, ItemAvailability.RESERVED);
            return true;
        }else {
            return false;
        }
    }


    public boolean checkInItem(LibraryItem libraryItem){
        if(isItemFromLibrary(libraryItem)){
            updateItemStatusInLibrary(libraryItem, ItemAvailability.AVAILABLE);
            return true;
        }else {
            return false;
        }

    }

    public List<LibraryItem> getItemsByStatus(List<LibraryItem> libraryItems, ItemAvailability itemAvailability){
        List<LibraryItem> filteredList = libraryItems.stream()
                .filter(item -> item.getStatus().equals(itemAvailability))
                .collect(Collectors.toList());
        return filteredList;
    }


}
