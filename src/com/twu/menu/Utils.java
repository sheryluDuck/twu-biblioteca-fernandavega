package com.twu.menu;

import com.twu.libraryItem.LibraryItem;

import java.util.InputMismatchException;
import java.util.List;

public class Utils {
    public static LibraryItem getLibraryItemFromList(List<LibraryItem> libraryItems, int selectedNumber){
        try{
            LibraryItem selectedItem= libraryItems.get(selectedNumber-1);
            return selectedItem;
        }catch (IndexOutOfBoundsException e){
            throw new InputMismatchException("There is no item with that number, sorry :( Try Again!");
        }
    }
}
