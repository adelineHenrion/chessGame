package org.isep.model.pieces;

import org.isep.model.Cell;
import org.isep.model.Position;

public abstract class Piece {
    protected Position position;
    protected boolean isWhite;

    protected Piece(Position position, boolean isWhite) {
        this.position = position;
        this.isWhite = isWhite;
    }

    public abstract boolean isValidMove(Position newPosition, Cell[][] board);

    public void updatePosition(Position newPosition) {
        this.position = newPosition;
    }

    public abstract String toString();

    public boolean isWhite() {
        return isWhite;
    }
}
