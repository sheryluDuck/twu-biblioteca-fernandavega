package com.twu.menu;

import com.twu.library.Library;
import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;
import com.twu.ui.UIActions;

import java.util.List;

public class ListItemsAction implements Action{
    private UIActions uiActions;
    private Library library;
    private Class itemsClass;
    private String header;

    public ListItemsAction(UIActions uiActions, Library library, Class itemsClass, String header) {
        this.uiActions = uiActions;
        this.library = library;
        this.itemsClass = itemsClass;
        this.header = header;
    }

    @Override
    public void run() {
        uiActions.printSpace();
        List<LibraryItem> libraryItems = library.getItemsByStatus(library.getItemList(itemsClass), ItemAvailability.AVAILABLE);
        if(libraryItems.size()<0){
           uiActions.print("Sorry, there are no items available :(");
        }else {
            Utils.printListOfItems(libraryItems, header);
            uiActions.printSpace();
        }
    }
}
