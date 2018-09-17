package com.twu.server;

import com.twu.book.Book;
import com.twu.library.Library;
import com.twu.menu.*;
import com.twu.movie.Movie;
import com.twu.operations.Middleware;
import com.twu.ui.UIActions;
import com.twu.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class Server {
    private List<User> userList = new ArrayList<>();
    private Middleware middleware;
    private Library library;
    private UIActions uiActions;

    public Server(Library library, UIActions uiActions) {
        this.library = library;
        this.uiActions = uiActions;
    }

    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    private void createMenu(User user){
        MenuComplete menu = new MenuComplete(uiActions);

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem(1, "List Available Books",
                new ListItemsAction(uiActions, library, Book.class, String.format("|%-3s|%-20s|%-50s|%-5s|", "N", "Author", "Book", "Year"))));
        options.add(new MenuItem(2, "List Available Movies",
                new ListItemsAction(uiActions, library, Movie.class, String.format("|%-3s|%-20s|%-50s|%-5s|%-3s|", "N", "Director", "Movie", "Year", "Rating"))));
        options.add(new MenuItem(3, "CheckIn/Out a Book",
                new SubmenuBooks(new MenuComplete(uiActions), library, uiActions, user)));
        options.add(new MenuItem(4, "CheckIn/Out a Movie",
                new SubmenuMovies(new MenuComplete(uiActions), library, uiActions, user)));
        options.add(new MenuItem(5, "User Information",
                new MsgAction(user.toString(), uiActions)));
        options.add(new MenuItem(6, "Library Record",
                new PrintLibraryRecordAction(library)));

        menu.newMenu("Main Menu", options);
    }

    public boolean logIn(String libraryNumber, String password) {
        if (middleware.check(libraryNumber, password)) {
            System.out.println("Authorization has been successful!");
            User loggedInUser = getUserByLibraryNumber(libraryNumber);
            createMenu(loggedInUser);

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
        return user.isValidPassword(libraryNumber, password);
    }
}
