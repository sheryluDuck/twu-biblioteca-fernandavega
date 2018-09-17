package com.twu.menu;

import com.twu.library.Library;
import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;
import com.twu.ui.UIActions;

import java.util.InputMismatchException;
import java.util.List;

public class CheckOutItemAction implements Action {
    private Library library;
    private UIActions uiActions;
    private Class itemsClass;
    private String header;

    public CheckOutItemAction(Library library, UIActions uiActions, Class itemsClass, String header) {
        this.library = library;
        this.uiActions = uiActions;
        this.itemsClass = itemsClass;
        this.header = header;
    }

    @Override
    public void run() {
        uiActions.printSpace();
        List<LibraryItem> libraryItems = library.getItemsByStatus(library.getItemList(itemsClass), ItemAvailability.AVAILABLE);
        if(libraryItems.size()<=0){
            uiActions.print("There are no items to checkOut");
        }else {
            Utils.printListOfItems(libraryItems, header);
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
