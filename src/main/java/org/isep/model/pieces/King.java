package org.isep.model.pieces;

import org.isep.model.Cell;
import org.isep.model.Position;

public class King extends Piece {
    public King(Position position, boolean isWhite) {
        super(position, isWhite);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        // Check if the new position is the same as the current position
        if(newPosition == this.position) return false;

        boolean isOccupied = board[newPosition.getY()][newPosition.getX()].getPiece() != null;
        int xDiff = Math.abs(this.position.getX() - newPosition.getX());
        int yDiff = Math.abs(this.position.getY() - newPosition.getY());

        // Normal move
        if(xDiff <= 1 && yDiff <= 1) {
            if(isOccupied) return board[newPosition.getY()][newPosition.getX()].getPiece().isWhite() != this.isWhite;
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        if (this.isWhite){
            return "♔";
        } else {
            return "♚";
        }
    }
}
