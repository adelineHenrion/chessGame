package org.isep.controller;

import org.isep.model.Cell;
import org.isep.model.Player;
import org.isep.model.Position;
import org.isep.model.pieces.*;
import org.isep.view.View;

import java.util.Random;

public class Chess {
    private final Cell[][] board;
    private final Player[] players;
    private Player currentPlayer;

    private final View view;

    public Chess(View view) {
        this.board = new Cell[8][8];
        this.players = new Player[2];
        this.view = view;
    }

    public void play() {
        view.print("Welcome to Random Chess Game");
        createPlayers();
        initializeBoard();
        while(!isCheckMate()) {
                printBoard();
                String move;
                do {
                    move = askMove();
                    if(isCheck()) {
                        view.print("You are in check!");
                    }
                } while (!isValidMove(move));
                updateBoard(move);
                switchPlayer();
                }


    }

    private void createPlayers() {
        boolean firstPlayerIsWhite = new Random().nextBoolean();
        // Ask the name of the players
        String name = view.askForString("Player " + 1 + " name: ");
        players[0] = new Player(name, firstPlayerIsWhite);
        view.print("Player " + 1 + " name: " + players[0].name());
        if(firstPlayerIsWhite) {
            view.print("Player " + 1 + " color: white");
            currentPlayer = players[0];
        }
            else {
            view.print("Player " + 1 + " color: black");
        }
        String name2 = view.askForString("Player " + 2 + " name: ");
        players[1] = new Player(name2, !firstPlayerIsWhite);
        view.print("Player " + 2 + " name: " + players[1].name());
        if(players[1].isWhite()) {
            view.print("Player " + 2 + " color: white");
            currentPlayer = players[1];
        }
        else {
            view.print("Player " + 1 + " color: black");
        }
    }

    private void initializeBoard() {
        // Create the board
        for (char i = 'a'; i <= 'h'; i++) {
            for (int j = 1; j <= 8; j++) {
                this.board[Position.convertColumn(i)][j - 1] = new Cell(new Position(i, j));
            }
        }

        // Initialize the pieces
        for (Player player : players) {
            player.initPieces();
            player.getPieces().forEach(piece ->
            {
                System.out.println(piece.getPosition().getX() + " " + piece.getPosition().getY());
                board[piece.getPosition().getY()][piece.getPosition().getX()].fillCell(piece);
            });
        }
    }

    private void printBoard() {
        view.printBoard(board);
    }

    private String askMove() {
        return view.askForMove().toLowerCase();
    }

    private boolean isValidPiece(String move) {
        Piece piece = board[Integer.parseInt(String.valueOf(move.charAt(1)))-1][Position.convertColumn(move.charAt(0))].getPiece();
        return piece != null && piece.isWhite() == currentPlayer.isWhite();
    }

    private boolean canMove(String move) {
        // TODO check if the piece can move and not cause a check
        return true;
    }

    private boolean isCheck() {
        // TODO (adeline) check if the current player is in check
        // get the king position in the board
        boolean isCheck = false;
        for (Piece piece : currentPlayer.getPieces()) {
            System.out.println(piece);
            if (piece instanceof King) {
                Position kingPosition = piece.getPosition();
                // check if the king is in check
                for (Player player : players) {
                    if (player != currentPlayer) {
                        for (Piece piece2 : player.getPieces()) {
                            if (piece2.isValidMove(kingPosition, board)) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
            }
        }
        return isCheck;
    }

    private boolean isCheckMate() {
        // TODO check if the current player is in checkmate
        return false;
    }

    private boolean isValidMove(String move) {
        boolean validPiece = isValidPiece(move.substring(0, 2));
        if(!validPiece) {
            view.print("Invalid piece");
            return false;
        } else {
            view.print("Valid piece");
            view.print(move);
            Piece piece = board[Integer.parseInt(String.valueOf(move.charAt(1)))-1][Position.convertColumn(move.charAt(0))].getPiece();
            return piece.isValidMove(new Position(move.substring(3, 5)), this.board) && canMove(move.substring(0, 2));
        }
    }

    private void updateBoard(String move) {
        int row = Integer.parseInt(String.valueOf(move.charAt(1)))-1;
        Piece piece = this.board[row][Position.convertColumn(move.charAt(0))].getPiece();
        Piece capturedPiece = this.board[Integer.parseInt(String.valueOf(move.charAt(4)))-1][Position.convertColumn(move.charAt(3))].getPiece();
        piece.updatePosition(new Position(move.substring(3, 5)));
        if(capturedPiece != null) {
            currentPlayer.removePiece(capturedPiece);
        }
        this.board[Integer.parseInt(String.valueOf(move.charAt(4)))-1][Position.convertColumn(move.charAt(3))].fillCell(piece);
        this.board[row][Position.convertColumn(move.charAt(0))].emptyCell();
    }

    private void switchPlayer() {
        if(currentPlayer == players[0]) {
            currentPlayer = players[1];
        } else {
            currentPlayer = players[0];
        }
    }
}
