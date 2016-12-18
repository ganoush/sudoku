package com.affinitas.sudoku.dto;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ganeshnagarajan on 12/18/16.
 */
public class ValidationResult {
    private boolean isValidMove;
    private boolean isSudokuComplete;
    private List<String> validationMessages;

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

    public List<String> getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(List<String> validationMessages) {
        this.validationMessages = validationMessages;
    }

    @Override
    public String toString(){
        return "isValidMove=" + this.isValidMove + ", isSudokuComplete=" + this.isSudokuComplete + ", validationMessages=" + this.validationMessages;
    }
}
