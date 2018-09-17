package com.twu.menu;

import com.twu.library.Library;
import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;
import com.twu.ui.UIActions;
import com.twu.user.User;

import java.util.InputMismatchException;
import java.util.List;

public class CheckInItemAction implements Action{
    private Library library;
    private UIActions uiActions;
    private User user;
    private Class itemsClass;
    private String header;

    public CheckInItemAction(Library library, UIActions uiActions, User user, Class itemsClass, String  header) {
        this.library = library;
        this.uiActions = uiActions;
        this.user = user;
        this.itemsClass = itemsClass;
        this.header = header;
    }

    @Override
    public void run() {
        uiActions.printSpace();
        List<LibraryItem> libraryItems = library.getItemsByStatus(library.getItemList(itemsClass), ItemAvailability.RESERVED);
        if(libraryItems.size()<=0){
            uiActions.print("There are no items to checkIn");
        }else {
            Utils.printListOfItems(libraryItems, header);
            try{
                uiActions.print("Please select an item number: ");
                String selectedNumberRaw = uiActions.readUserInputFromConsole();
                int selectedNumber = uiActions.castNumberFromConsole(selectedNumberRaw);
                LibraryItem selectedItem = Utils.getLibraryItemFromList(libraryItems, selectedNumber);
                boolean isSuccessfulCheckIn = library.checkInItem(selectedItem);
                if (isSuccessfulCheckIn){
                    uiActions.print("Thank you for returning the item.");
                    library.registerCheckOut(user, selectedItem);
                }else{
                    uiActions.print("That is not a valid item to return.");
                }
            }catch (InputMismatchException e){
                uiActions.print(e.getMessage());
            }
        }
    }
}
