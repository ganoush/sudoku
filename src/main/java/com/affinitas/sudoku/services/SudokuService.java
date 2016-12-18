package com.affinitas.sudoku.services;

import com.affinitas.sudoku.dto.SudokuMove;
import com.affinitas.sudoku.dto.ValidationResult;

/**
 * Created by ganeshnagarajan on 12/17/16.
 */
public interface SudokuService {
    int[][] loadSudokuBoard();
    ValidationResult validateMove(SudokuMove sudokuMove);
}
