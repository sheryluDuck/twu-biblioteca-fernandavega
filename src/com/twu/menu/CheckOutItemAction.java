package com.twu.menu;

import com.twu.library.Library;
import com.twu.libraryItem.LibraryItem;
import com.twu.ui.UIActions;

import java.util.InputMismatchException;
import java.util.List;

public class CheckOutItemAction implements Action {
    private Library library;
    private List<LibraryItem> libraryItems;
    private UIActions uiActions;

    public CheckOutItemAction(Library library, List<LibraryItem> libraryItemList, UIActions uiActions) {
        this.library = library;
        this.libraryItems = libraryItemList;
        this.uiActions = uiActions;
    }

    @Override
    public void run() {
        uiActions.printSpace();
        if(libraryItems.size()<0){
            uiActions.print("There are no items to checkOut");
        }else {
            try{
                uiActions.print("Please select an item number: ");
                String selectedNumberRaw = uiActions.readUserInputFromConsole();
                int selectedNumber = uiActions.castNumberFromConsole(selectedNumberRaw);
                LibraryItem selectedItem = Utils.getLibraryItemFromList(libraryItems, selectedNumber);
                boolean isSuccessfulCheckOut = library.checkOutItem(selectedItem);
                if (isSuccessfulCheckOut){
                    uiActions.print("Thank you! Enjoy the item");
                }else{
                    uiActions.print("That item is not available.");
                }
            }catch (InputMismatchException e){
                uiActions.print(e.getMessage());
            }
        }
    }
}
