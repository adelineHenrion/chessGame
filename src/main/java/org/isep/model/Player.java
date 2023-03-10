package org.isep.model;

public class Player {
    private final String name;
    private final boolean isWhite;

    private boolean isCheck = false;

    public Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
    }

    public String name() {
        return name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isCheck() {
        return isCheck;
    }
}
