package com.twu.biblioteca;


import com.twu.book.Book;
import com.twu.library.Library;
import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;
import com.twu.movie.Movie;
import com.twu.operations.Middleware;
import com.twu.operations.RoleCheckMiddleware;
import com.twu.operations.UserExistsMiddleware;
import com.twu.server.Server;
import com.twu.ui.UIActions;
import com.twu.user.User;
import com.twu.user.UserType;

import java.io.*;
import java.util.*;

import static sun.java2d.cmm.ColorTransform.In;

public class BibliotecaApp {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;


    private static void init() {
        UIActions uiActions = new UIActions(new Scanner(System.in), new PrintStream(System.out));
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
        middleware.linkWith(new RoleCheckMiddleware(server));

        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter your library number: ");
            String libraryNumber = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(libraryNumber, password);
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
