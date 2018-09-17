package com.twu.menu;

import com.twu.library.Library;
import com.twu.libraryItem.ItemAvailability;
import com.twu.ui.UIActions;
import com.twu.user.User;

import java.util.ArrayList;
import java.util.List;

public class SubmenuBooks implements Action {

    private MenuComplete menu;
    private Library library;
    private UIActions uiActions;
    private User user;

    public SubmenuBooks(MenuComplete menu, Library library, UIActions uiActions, User user) {
        this.menu = menu;
        this.library = library;
        this.uiActions = uiActions;
        this.user = user;
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem(1, "CheckOut a Book",
                new CheckOutItemAction(library, library.getItemsByStatus(library.getBookList(), ItemAvailability.AVAILABLE), uiActions )));
        options.add(new MenuItem(2,"CheckIn a Book",
                new CheckInItemAction(library, uiActions, library.getItemsByStatus(library.getBookList(), ItemAvailability.RESERVED), user)));
        menu.newMenu("Book Options: ", options);
    }

}
