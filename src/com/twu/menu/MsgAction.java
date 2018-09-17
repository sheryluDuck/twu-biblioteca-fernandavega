package com.twu.menu;

import com.twu.ui.UIActions;

public class MsgAction implements Action {
    private  UIActions uiActions;
    private String msg;

    public MsgAction(String message, UIActions uiActions) {
        this.msg = message;
        this.uiActions = uiActions;
    }

    public void run() {
        uiActions.printSpace();
        uiActions.print(msg);
    }

}
