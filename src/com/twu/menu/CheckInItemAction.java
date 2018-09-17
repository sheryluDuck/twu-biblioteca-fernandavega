package com.twu.menu;

import com.twu.library.Library;
import com.twu.libraryItem.LibraryItem;
import com.twu.ui.UIActions;
import com.twu.user.User;

import javax.jws.soap.SOAPBinding;
import java.util.InputMismatchException;
import java.util.List;

public class CheckInItemAction implements Action{
    private Library library;
    private UIActions uiActions;
    private List<LibraryItem> libraryItems;
    private User user;

    public CheckInItemAction(Library library, UIActions uiActions, List<LibraryItem> libraryItems, User user) {
        this.library = library;
        this.uiActions = uiActions;
        this.libraryItems = libraryItems;
        this.user = user;
    }

    @Override
    public void run() {
        uiActions.printSpace();
        if(libraryItems.size()<0){
            uiActions.print("There are no items to checkIn");
        }else {
            libraryItems.stream().map(LibraryItem::toString).forEach(UIActions::print);
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
