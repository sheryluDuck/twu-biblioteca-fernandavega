package com.twu.server;

import com.twu.book.Book;
import com.twu.library.Library;
import com.twu.libraryItem.ItemAvailability;
import com.twu.libraryItem.LibraryItem;
import com.twu.menu.ListItemsAction;
import com.twu.menu.MenuComplete;
import com.twu.menu.MenuItem;
import com.twu.menu.MsgAction;
import com.twu.movie.Movie;
import com.twu.operations.Middleware;
import com.twu.ui.UIActions;
import com.twu.user.User;
import com.twu.user.UserType;

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
                new ListItemsAction(library.getItemsByStatus(library.getBookList(), ItemAvailability.AVAILABLE), uiActions)));
        options.add(new MenuItem(2, "List Available Movies",
                new ListItemsAction(library.getItemsByStatus(library.getMovieList(), ItemAvailability.AVAILABLE), uiActions)));
        options.add(new MenuItem(3, "User Information",
                new MsgAction(user.toString(), uiActions)));

        menu.newMenu("Main Menu", options);
    }

    public boolean logIn(String libraryNumber, String password) {
        if (middleware.check(libraryNumber, password)) {
            System.out.println("Authorization have been successful!");
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
        return user.getPassword().equals(password);
    }
}
