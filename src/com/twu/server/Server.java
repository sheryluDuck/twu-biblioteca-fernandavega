package com.twu.server;

import com.twu.operations.Middleware;
import com.twu.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Server {
    private List<User> userList = new ArrayList<>();
    private Middleware middleware;

    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    public boolean logIn(String libraryNumber, String password) {
        if (middleware.check(libraryNumber, password)) {
            System.out.println("Authorization have been successful!");

            // Do something useful here for authorized users.

            return true;
        }
        return false;
    }

    public void register(User user) {
        userList.add(user);
    }

    public boolean hasUser(String libraryNumber) {
        User matchUser = getUserByLibraryNumber(libraryNumber);
        return matchUser != null;
    }

    public User getUserByLibraryNumber(String libraryNumber){
        List <User> resultUsers = userList.stream()
                .filter(user -> user.getLibraryNumber().equals(libraryNumber))
                .collect(Collectors.toList());
        return resultUsers.size() > 0 ? resultUsers.get(0) : null;
    }

    public boolean isValidPassword(String libraryNumber, String password) {
        User user = getUserByLibraryNumber(libraryNumber);
        return user.getPassword().equals(password);
    }
}
