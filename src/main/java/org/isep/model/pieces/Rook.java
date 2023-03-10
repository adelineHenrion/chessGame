package org.isep.model.pieces;

import org.isep.model.Cell;
import org.isep.model.Position;

public class Rook extends Piece{
    public Rook(Position position, boolean isWhite) {
        super(position, isWhite);
    }

    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        if (newPosition == this.position) return false;

        boolean isOccupied = board[newPosition.getY()][newPosition.getX()].getPiece() != null;
        int xDiff = Math.abs(this.position.getX() - newPosition.getX());
        int yDiff = Math.abs(this.position.getY() - newPosition.getY());

            // Check if the path is clear
            if(xDiff == 0 && yDiff != 0) {
                int yMin = Math.min(this.position.getY(), newPosition.getY());
                int yMax = Math.max(this.position.getY(), newPosition.getY());
                for(int i = yMin + 1; i < yMax; i++) {
                    if(board[i][this.position.getX()].getPiece() != null) return false;
                }
                return !isOccupied || board[newPosition.getY()][newPosition.getX()].getPiece().isWhite() != this.isWhite;
            } else if(xDiff != 0 && yDiff == 0) {
                int xMin = Math.min(this.position.getX(), newPosition.getX());
                int xMax = Math.max(this.position.getX(), newPosition.getX());
                for(int i = xMin + 1; i < xMax; i++) {
                    if(board[this.position.getY()][i].getPiece() != null) return false;
                }
                return !isOccupied || board[newPosition.getY()][newPosition.getX()].getPiece().isWhite() != this.isWhite;
            }
        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite){
            return "♖";
        } else {
            return "♜";
        }
    }
}
