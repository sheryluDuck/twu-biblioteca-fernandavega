package com.twu.menu;

import com.twu.ui.UIActions;
import com.twu.user.User;

public class PrintUserInfoAction implements Action{
    private User user;
    private UIActions uiActions;

    public PrintUserInfoAction(User user, UIActions uiActions) {
        this.user = user;
        this.uiActions = uiActions;
    }

    @Override
    public void run() {
        uiActions.printSpace();
        uiActions.print(user.toString());
    }
}
