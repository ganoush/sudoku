package com.affinitas.sudoku.dto.response;

/**
 * Created by ganeshnagarajan on 12/17/16.
 */
public class SudokuLoadResponse extends SudokuResponse{
    private int[][] sudokuBoard;

    public int[][] getSudokuBoard() {
        return sudokuBoard;
    }

    public void setSudokuBoard(int[][] sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }
}
