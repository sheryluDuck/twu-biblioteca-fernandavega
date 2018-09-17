package com.twu.menu;

import com.twu.ui.UIActions;

import java.util.*;

public class MenuComplete {

    private UIActions uiActions;

    public MenuComplete(UIActions uiActions) {
        this.uiActions = uiActions;
    }


    public void newMenu(String title, List<MenuItem> items) {
        Map<Integer, Action> actions = new HashMap<>();
        List<Integer> options = new ArrayList<>();
        String selectedOption;
        //Key option;
        MenuItem quit = new MenuItem(0, "Exit :(", new MsgAction("Come back again!", uiActions));

        uiActions.print(title);

        for (MenuItem item : items) {
            options.add(item.getOption());
            actions.put(item.getOption(), item.getAction());
        }
        items.add(quit);
        options.add(quit.getOption());
        actions.put(quit.getOption(), quit.getAction());

        do {
            for (MenuItem item : items) {
                uiActions.print(item.toString());
            }

            selectedOption = uiActions.readUserInputFromConsole();
            try {
                int selectedNumber = Utils.castNumberFromConsole(selectedOption);
                Action action = actions.get(selectedNumber);
                action.run();

            } catch (InputMismatchException | NullPointerException e) {
                uiActions.print("Sorry, this is not a valid option");
            }

        } while (!selectedOption.equals("0"));

    }
}
