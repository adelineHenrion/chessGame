package org.isep.model;

import org.isep.model.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final boolean isWhite;
    private List<Piece> pieces = new ArrayList<>();

    public Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
        initPieces();
    }

    public void initPieces() {
        for (char i = 'a'; i <= 'h'; i++) {
            pieces.add(new Pawn(new Position(i, this.isWhite ? 2: 7),this.isWhite));
        }
        pieces.add(new Rook(new Position('a', this.isWhite ? 1 : 8),this.isWhite));
        pieces.add(new Rook(new Position('h', this.isWhite ? 1 : 8),this.isWhite));
        pieces.add(new Knight(new Position('b', this.isWhite ? 1 : 8),this.isWhite));
        pieces.add(new Knight(new Position('g', this.isWhite ? 1 : 8),this.isWhite));
        pieces.add(new Bishop(new Position('c', this.isWhite ? 1 : 8),this.isWhite));
        pieces.add(new Bishop(new Position('f', this.isWhite ? 1 : 8),this.isWhite));
        pieces.add(new Queen(new Position('d', this.isWhite ? 1 : 8),this.isWhite));
        pieces.add(new King(new Position('e', this.isWhite ? 1 : 8),this.isWhite));
    }

    public String name() {
        return name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isCheck() {
        // TODO (adeline) Check if the player is in check
        return false;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }
}
