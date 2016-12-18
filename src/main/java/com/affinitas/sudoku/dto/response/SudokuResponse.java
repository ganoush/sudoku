package com.affinitas.sudoku.dto.response;

/**
 * Created by ganeshnagarajan on 12/17/16.
 */
public class SudokuResponse {
    private boolean isValidMove;
    private boolean isSudokuComplete;

    public boolean isValidMove() {
        return isValidMove;
    }

    public void setValidMove(boolean validMove) {
        isValidMove = validMove;
    }

    public boolean isSudokuComplete() {
        return isSudokuComplete;
    }

    public void setSudokuComplete(boolean sudokuComplete) {
        isSudokuComplete = sudokuComplete;
    }
}
