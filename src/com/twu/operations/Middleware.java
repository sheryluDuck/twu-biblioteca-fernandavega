package com.twu.operations;

public abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(String libraryNumber, String password);


    protected boolean checkNext(String libraryNumber, String password) {
        if (next == null) {
            return true;
        }
        return next.check(libraryNumber, password);
    }
}
