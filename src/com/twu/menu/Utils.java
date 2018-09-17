package com.twu.menu;

import com.twu.libraryItem.LibraryItem;
import com.twu.ui.UIActions;

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

    public static void printListOfItems(List<LibraryItem> libraryItems, String header){
        int counter = 0;
        UIActions.print(header);
        for (LibraryItem item:
                libraryItems) {
            counter++;
            UIActions.print(String.format("|%-2d %-1s", counter, item.toString()));
        }
    }
}
