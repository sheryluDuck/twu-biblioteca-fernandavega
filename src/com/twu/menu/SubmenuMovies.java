package com.twu.menu;

import com.twu.library.Library;
import com.twu.movie.Movie;
import com.twu.ui.UIActions;
import com.twu.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubmenuMovies implements Action {
    private MenuComplete menu;
    private Library library;
    private UIActions uiActions;
    private User user;

    public SubmenuMovies(MenuComplete menu, Library library, UIActions uiActions, User user) {
        this.menu = menu;
        this.library = library;
        this.uiActions = uiActions;
        this.user = user;
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem(1, "CheckOut a Movie",
                new CheckOutItemAction(library, uiActions, Movie.class, String.format("|%-3s|%-20s|%-50s|%-5s|%-3s|", "N", "Director", "Movie", "Year", "Rating"))));
        options.add(new MenuItem(2,"CheckIn a Movie",
                new CheckInItemAction(library, uiActions, user, Movie.class, String.format("|%-3s|%-20s|%-50s|%-5s|%-3s|", "N", "Director", "Movie", "Year", "Rating"))));
        menu.newMenu("Movie Options: ", options);
    }
}
