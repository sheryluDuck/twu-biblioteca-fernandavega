package com.twu.library;

import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;
import com.twu.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
    private String libraryName;
    private List<LibraryItem> libraryItems;
    private Map <User, List<LibraryItem>> registryBook;

    public Library(String libraryName, List<LibraryItem> libraryItems, Map<User, List<LibraryItem>> registryBook) {
        this.libraryName = libraryName;
        this.libraryItems = libraryItems;
        this.registryBook = registryBook;
    }

    public List<LibraryItem> getItemList(Class itemClass) {
        List<LibraryItem> bookList = libraryItems.stream()
                .filter(item -> itemClass.isInstance(item))
                .collect(Collectors.toList());
        return bookList;
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

    private boolean isUserInRegistryBook(User regularUser){
        return registryBook.containsKey(regularUser);
    }


    public void registerCheckOut(User regularUser, LibraryItem libraryItem){

        List<LibraryItem> userCheckedOutItems;

        if(isUserInRegistryBook(regularUser)){
            userCheckedOutItems = registryBook.get(regularUser);
            userCheckedOutItems.add(libraryItem);
        } else{
            userCheckedOutItems = new ArrayList<>(Arrays.asList(libraryItem));
        }
        registryBook.put(regularUser, userCheckedOutItems);
    }

    public Map<User, List<LibraryItem>> getLibraryRegistryBook(){
        return this.registryBook;
    }


}
