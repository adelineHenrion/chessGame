package org.isep.view;

import org.isep.model.Cell;

import java.util.Scanner;

public class View {

    static SafeScanner scanner = new SafeScanner(new Scanner(System.in));
    public void print(String message) {
        System.out.println(message);
    }

    public void printBoard(Cell[][] board) {
        System.out.println("    a  b  c  d  e  f  g  h");
        for (int i = 8; i > 0; i--) {
            System.out.print(" " + i + " ");
            for (Cell cell : board[i-1]) {
                if (cell.isEmpty()) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" " + cell.getPiece() + " ");
                }
            }
            System.out.print(" " + i + " ");
            System.out.println();
        }
        System.out.println("    a  b  c  d  e  f  g  h");
    }

    public String askForString(String message) {
        System.out.print(message);
        return scanner.getString();
    }

    public String askForMove() {
        this.print("Enter your move: (location of the piece to move, location to move to) ");
        return scanner.getStringRegex("^[a-hA-H][1-8]\\s[a-hA-H][1-8]$");
    }
}
