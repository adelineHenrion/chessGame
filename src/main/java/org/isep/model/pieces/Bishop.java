package org.isep.model.pieces;

import org.isep.model.Cell;
import org.isep.model.Position;

public class Bishop extends Piece {

    public Bishop(Position position, boolean isWhite) {
        super(position, isWhite);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        if(newPosition == this.position) return false;

        boolean isOccupied = board[newPosition.getY()][newPosition.getX()].getPiece() != null;
        int xDiff = Math.abs(this.position.getX() - newPosition.getX());
        int yDiff = Math.abs(this.position.getY() - newPosition.getY());

        // Normal move
        if(xDiff == yDiff) {
            // Check if there is a piece in the way
            int xDir = (newPosition.getX() - this.position.getX()) / xDiff;
            int yDir = (newPosition.getY() - this.position.getY()) / yDiff;
            for(int i = 1; i < xDiff; i++) {
                if(board[this.position.getY() + i * yDir][this.position.getX() + i * xDir].getPiece() != null) return false;
            }
            if(isOccupied) return board[newPosition.getY()][newPosition.getX()].getPiece().isWhite() != this.isWhite;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite){
            return "♗";
        } else {
            return "♝";
        }
    }
}
