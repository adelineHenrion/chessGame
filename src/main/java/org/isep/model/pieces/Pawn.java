package org.isep.model.pieces;

import org.isep.model.Cell;
import org.isep.model.Position;

public class Pawn extends Piece{
    public Pawn(Position position, boolean isWhite) {
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
        if(xDiff == 0 && yDiff == 1) {
            if(this.isWhite && newPosition.getY() > this.position.getY()) return !isOccupied;
            if(!this.isWhite && newPosition.getY() < this.position.getY()) return !isOccupied;
        }
        // First move
        if(xDiff == 0 && yDiff == 2) {
            if(this.isWhite && newPosition.getY() > this.position.getY() && this.position.getY() == 1) return !isOccupied;
            if(!this.isWhite && newPosition.getY() < this.position.getY() && this.position.getY() == 6) return !isOccupied;
        }

        // Capture
        if(xDiff == 1 && yDiff == 1 && isOccupied) {
            boolean isOccupiedPieceWhite = board[newPosition.getY()][newPosition.getX()].getPiece().isWhite();
            if(this.isWhite && newPosition.getY() > this.position.getY()) return (isOccupiedPieceWhite != this.isWhite);
            if(!this.isWhite && newPosition.getY() < this.position.getY()) return (isOccupiedPieceWhite != this.isWhite);
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite){
            return "♙";
        } else {
            return "♟";
        }
    }
}
