package com.twu.biblioteca;


import com.twu.book.Book;
import com.twu.library.Library;
import com.twu.operations.Middleware;
import com.twu.operations.RoleCheckMiddleware;
import com.twu.operations.UserExistsMiddleware;
import com.twu.server.Server;
import com.twu.ui.UIActions;
import com.twu.user.User;
import com.twu.user.UserType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BibliotecaApp {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register(new User("Smithy", "sm@t.com", "Middle of nowhere", "73734435", "123-4567", "test123", UserType.CUSTOMER));
        server.register(new User("John Smith", "js@t.com", "Fake St", "634767436", "123-8910", "test456", UserType.LIBRARIAN));

        Middleware middleware = new UserExistsMiddleware(server);
        middleware.linkWith(new RoleCheckMiddleware(server));

        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter your library number: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }



    /*public static void main(String[] args) {

        Book[] libraryBooks= {
                new Book("Plato", "Republic", 1984, Book.BookAvailabilityStatus.AVAILABLE),
                new Book("Michel Foucault", "The Order of Things", 1966, Book.BookAvailabilityStatus.RESERVED),
                new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE),
                new Book("Camilo Jose Cela", "Colmena", 1950, Book.BookAvailabilityStatus.AVAILABLE)
        };
        Library awesomeLibrary = new Library("Awesome Library", Arrays.asList(libraryBooks));
       UIActions printer = new UIActions();
       printer.mainMenu(awesomeLibrary);


    }*/
}
