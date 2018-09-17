package com.twu.menu;

import com.twu.libraryItem.LibraryItem;
import com.twu.ui.UIActions;
import com.twu.user.User;

import java.util.Map;

public class PrintLibraryRecordAction implements Action{
    private Map<User, LibraryItem> libraryRecord;

    public PrintLibraryRecordAction(Map<User, LibraryItem> libraryRecord) {
        this.libraryRecord = libraryRecord;
    }

    @Override
    public void run() {
        libraryRecord.forEach((k,v)-> UIActions.print("user: " + k.getName() + ", item: " + v.getName()));
    }
}
