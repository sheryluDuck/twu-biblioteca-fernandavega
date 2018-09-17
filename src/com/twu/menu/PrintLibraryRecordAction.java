package com.twu.menu;

import com.twu.library.Library;
import com.twu.libraryItem.LibraryItem;
import com.twu.ui.UIActions;
import com.twu.user.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintLibraryRecordAction implements Action{
    private Library library;

    public PrintLibraryRecordAction(Library library) {
        this.library = library;
    }

    @Override
    public void run() {
        Map<User, List<LibraryItem>> libraryRecord = library.getLibraryRegistryBook();
        libraryRecord.forEach((k,v)-> UIActions.print("user: " + k.getName() + ", items: " + v.stream().map(LibraryItem::getItemName).collect(Collectors.joining(", "))));
    }
}
