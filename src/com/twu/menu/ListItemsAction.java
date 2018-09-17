package com.twu.menu;

import com.twu.library.Library;
import com.twu.libraryItem.LibraryItem;
import com.twu.ui.UIActions;

import java.util.List;

public class ListItemsAction implements Action{
    private List<LibraryItem> libraryItems;
    private UIActions uiActions;

    public ListItemsAction(List<LibraryItem> libraryItems, UIActions uiActions) {
        this.libraryItems = libraryItems;
        this.uiActions = uiActions;
    }

    @Override
    public void run() {
        uiActions.printSpace();
        if(libraryItems.size()<0){
           uiActions.print("Sorry, there are no items available :(");
        }else {
            libraryItems.stream().map(LibraryItem::toString).forEach(UIActions::print);
        }
    }
}
