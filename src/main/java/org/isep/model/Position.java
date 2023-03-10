package org.isep.model;

public class Position {
    private char column;
    private int row;

    public Position(int column, int row) {
        switch (column) {
            case 0 -> this.column = 'a';
            case 1 -> this.column = 'b';
            case 2 -> this.column = 'c';
            case 3 -> this.column = 'd';
            case 4 -> this.column = 'e';
            case 5 -> this.column = 'f';
            case 6 -> this.column = 'g';
            case 7 -> this.column = 'h';
            default -> throw new IllegalArgumentException("Invalid column: " + column);
        }
        if(row < 1 || row > 8) throw new IllegalArgumentException("Invalid row: " + row);
        this.row = row;
    }

    public Position(String position){
        if(position.length() != 2) throw new IllegalArgumentException("Invalid position: " + position);
        String newPosition = position.toLowerCase();
        if (newPosition.charAt(0) < 'a' || newPosition.charAt(0) > 'h') throw new IllegalArgumentException("Invalid column: " + position.charAt(0));
        if (newPosition.charAt(1) < '1' || newPosition.charAt(1) > '8') throw new IllegalArgumentException("Invalid row: " + position.charAt(1));
        this.column = newPosition.charAt(0);
        this.row = Integer.parseInt(newPosition.substring(1,2));
    }

    public String toString() {
        return "" + column + row;
    }

    public static int convertColumn(char column) {
        column = Character.toLowerCase(column);
        switch (column) {
            case 'a' -> { return 0; }
            case 'b' -> { return 1; }
            case 'c' -> { return 2; }
            case 'd' -> { return 3; }
            case 'e' -> { return 4; }
            case 'f' -> { return 5; }
            case 'g' -> { return 6; }
            case 'h' -> { return 7; }
            default -> throw new IllegalArgumentException("Invalid column: " + column);
        }
    }

    public int getX() {
        return convertColumn(column);
    }

    public int getY() {
        return row-1;
    }
}


