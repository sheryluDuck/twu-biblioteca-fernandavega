package com.twu.operations;

import com.twu.server.Server;

public class UserPasswordCheckMiddleware extends Middleware {

    private Server server;

    public UserPasswordCheckMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String libraryNumber, String password) {
        if (!server.isValidPassword(libraryNumber, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(libraryNumber, password);
    }
}
