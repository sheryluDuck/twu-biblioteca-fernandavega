package com.twu.biblioteca;


import com.twu.book.Book;
import com.twu.library.Library;
import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;
import com.twu.movie.Movie;
import com.twu.operations.Middleware;
import com.twu.operations.RoleCheckMiddleware;
import com.twu.operations.UserExistsMiddleware;
import com.twu.operations.UserPasswordCheckMiddleware;
import com.twu.server.Server;
import com.twu.ui.UIActions;
import com.twu.user.User;
import com.twu.user.UserType;

import java.io.*;
import java.util.*;


public class BibliotecaApp {

    private static Server server;
    private static InputStream inputStream = System.in;
    private static UIActions uiActions = new UIActions(inputStream, new PrintStream(System.out));


    private static void init() {

        List<LibraryItem> libraryItems = new ArrayList<LibraryItem>(Arrays.asList(new Book("Plato", "Republic", 1984, ItemAvailability.AVAILABLE),
                new Book("Michel Foucault", "The Order of Things", 1966, ItemAvailability.RESERVED),
                new Book("Camilo Jose Cela", "Colmena", 1950, ItemAvailability.AVAILABLE),
                new Movie("Back to the future I", "Steven Spielberg", 1985, 10, ItemAvailability.AVAILABLE),
                new Movie("Saturday Night Fever", "John Badham", 1977, 9, ItemAvailability.RESERVED),
                new Movie("Scream", "Wes Craven", 1996, 7, ItemAvailability.AVAILABLE)
        ));
        List<User> libraryUsers = new ArrayList<>(Arrays.asList(
                new User("Smithy", "sm@t.com", "Middle of nowhere", "73734435", "123-4567", "test123",UserType.CUSTOMER),
                new User("John Smith", "js@t.com", "Fake St", "634767436", "123-8910", "test456", UserType.CUSTOMER),
                new User("Jane Doe", "jd@t.com", "Papaya St", "859485488", "123-1112", "test789", UserType.CUSTOMER)
        ));
        Map<User, List<LibraryItem>> awesomeRecordBook =  new HashMap<>();
        awesomeRecordBook.put(libraryUsers.get(0), new ArrayList<>(Arrays.asList(
                new Book("Michel Foucault", "The Order of Things", 1966, ItemAvailability.RESERVED),
                new Movie("Back to the future I", "Steven Spielberg", 1985, 10, ItemAvailability.AVAILABLE)
        )));
        awesomeRecordBook.put(libraryUsers.get(1), new ArrayList<>(Arrays.asList(
                new Movie("Scream", "Wes Craven", 1996, 7, ItemAvailability.AVAILABLE)
        )));

        Library awesomeLibrary = new Library("Librimundi", libraryItems, awesomeRecordBook);
        server = new Server(awesomeLibrary, uiActions);
        server.register(new User("Smithy", "sm@t.com", "Middle of nowhere", "73734435", "123-4567", "test123", UserType.CUSTOMER));
        server.register(new User("John Smith", "js@t.com", "Fake St", "634767436", "123-8910", "test456", UserType.LIBRARIAN));

        Middleware middleware = new UserExistsMiddleware(server);
        middleware.linkWith(new UserPasswordCheckMiddleware(server))
                .linkWith(new RoleCheckMiddleware(server));

        server.setMiddleware(middleware);
    }

    public static void main(String[] args){
        init();

       boolean success;
        do {
            UIActions.print("Enter your library number: ");
            String libraryNumber = uiActions.readUserInputFromConsole();
            UIActions.print("Input password: ");
            String password = uiActions.readUserInputFromConsole();
            success = server.logIn(libraryNumber, password);
        } while (!success);
    }
}
