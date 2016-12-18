package com.affinitas.sudoku.dto;

import java.util.Arrays;

/**
 * Created by ganeshnagarajan on 12/18/16.
 */
public class SudokuMove {
    private int[][] sudokuBoard;
    private int moveRow;
    private int moveColumn;
    private int moveValue;

    public SudokuMove(){
    }

    public SudokuMove(int[][] sudokuBoard, int moveRow, int moveColumn, int moveValue){
        this.sudokuBoard = sudokuBoard;
        this.moveRow = moveRow;
        this.moveColumn = moveColumn;
        this.moveValue = moveValue;
    }

    public int[][] getSudokuBoard() {
        return sudokuBoard;
    }

    public void setSudokuBoard(int[][] sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public int getMoveRow() {
        return moveRow;
    }

    public void setMoveRow(int moveRow) {
        this.moveRow = moveRow;
    }

    public int getMoveColumn() {
        return moveColumn;
    }

    public void setMoveColumn(int moveColumn) {
        this.moveColumn = moveColumn;
    }

    public int getMoveValue() {
        return moveValue;
    }

    public void setMoveValue(int moveValue) {
        this.moveValue = moveValue;
    }

    @Override
    public String toString(){
        return "SudokuBoard=" + Arrays.deepToString(this.sudokuBoard) + ", row=" + this.moveRow +
                ", column=" + this.moveColumn + ", value=" + this.moveValue;
    }
}
