package org.isep.model;

import org.isep.model.pieces.Piece;

public class Cell {
    private final Position position;
    private boolean isEmpty;

    public Piece piece;
    public Cell(Position position) {
        this.position = position;
        this.isEmpty = true;
    }

    public void fillCell(Piece piece) {
        this.piece = piece;
        this.isEmpty = false;
    }

    public void emptyCell() {
        this.piece = null;
        this.isEmpty = true;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
