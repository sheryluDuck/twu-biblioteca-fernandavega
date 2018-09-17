package com.twu.menu;

public class MenuItem {
    private int id;
    private String description;
    private Action action;

    public MenuItem(int id, String description, Action action) {
        this.id = id;
        this.description = description;
        this.action = action;
    }

    public int getOption() {
        return id;
    }

    public String toString() {
        return String.format("%s. %s", id, description);
    }

    public void run() {
        action.run();
    }

    public Action getAction() {
        return action;
    }
}
