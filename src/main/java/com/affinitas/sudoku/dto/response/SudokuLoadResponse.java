package com.affinitas.sudoku.dto.response;

import java.util.Arrays;

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

    @Override
    public String toString(){
        return "SudokuBoard=" + Arrays.deepToString(this.sudokuBoard) + super.toString();
    }
}
