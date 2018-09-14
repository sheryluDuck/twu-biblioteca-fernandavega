package com.twu.operations;

import com.twu.server.Server;

public class UserExistsMiddleware extends Middleware{

    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String libraryNumber, String password) {
        if (!server.hasUser(libraryNumber)) {
            System.out.println("This user is not registered!");
            return false;
        }
        if (!server.isValidPassword(libraryNumber, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(libraryNumber, password);
    }
}
