package org.isep.controller;

import org.isep.model.Cell;
import org.isep.model.Player;
import org.isep.model.Position;
import org.isep.model.pieces.*;
import org.isep.view.View;

import java.util.Arrays;
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
            initializeBoard();
            createPlayers();
            while(!isCheckMate()) {
                printBoard();
                String move;
                do {
                    move = askMove();
                    if(isCheck(move)) {
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
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                this.board[i-1][j-1] = new Cell(new Position(i-1, j));
            }
        }
        for (int i = 0; i < 8; i++) {
            this.board[1][i].fillCell(new Pawn(new Position(i, 2), true));
            this.board[6][i].fillCell(new Pawn(new Position(i, 7), false));
        }
        this.board[0][0].fillCell(new Rook(new Position(0, 1), true));
        this.board[0][7].fillCell(new Rook(new Position(7, 1), true));
        this.board[7][0].fillCell(new Rook(new Position(0, 8), false));
        this.board[7][7].fillCell(new Rook(new Position(7, 8), false));
        this.board[0][1].fillCell(new Knight(new Position(1, 1), true));
        this.board[0][6].fillCell(new Knight(new Position(6, 1), true));
        this.board[7][1].fillCell(new Knight(new Position(1, 8), false));
        this.board[7][6].fillCell(new Knight(new Position(6, 8), false));
        this.board[0][2].fillCell(new Bishop(new Position(2, 1), true));
        this.board[0][5].fillCell(new Bishop(new Position(5, 1), true));
        this.board[7][2].fillCell(new Bishop(new Position(2, 8), false));
        this.board[7][5].fillCell(new Bishop(new Position(5, 8), false));
        this.board[0][3].fillCell(new Queen(new Position(3, 1), true));
        this.board[7][3].fillCell(new Queen(new Position(3, 8), false));
        this.board[0][4].fillCell(new King(new Position(4, 1), true));
        this.board[7][4].fillCell(new King(new Position(4, 8), false));

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

    private boolean isCheck(String move) {
        // TODO (adeline) check if the current player is in check
        // get the king position in the board
        System.out.println("hello");
        return false;
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
        piece.updatePosition(new Position(move.substring(3, 5)));
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
