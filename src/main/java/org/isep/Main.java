package org.isep;

import org.isep.controller.Chess;
import org.isep.view.View;

public class Main {
    public static void main(String[] args) {
        Chess chess = new Chess(new View());
        chess.play();
    }
}
