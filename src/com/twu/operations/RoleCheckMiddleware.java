package com.twu.operations;

import com.twu.server.Server;
import com.twu.ui.UIActions;
import com.twu.user.User;
import com.twu.user.UserType;

public class RoleCheckMiddleware extends Middleware {

    private Server server;

    public RoleCheckMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String libraryNumber, String password) {
        User user = server.getUserByLibraryNumber(libraryNumber);
        if (user.getUserType().equals(UserType.LIBRARIAN)) {
            UIActions.print("Hello, librarian!");
            return true;
        }
        UIActions.print("Hello, user!");
        return checkNext(libraryNumber, password);
    }
}
