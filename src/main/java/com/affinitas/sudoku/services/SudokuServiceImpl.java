package com.affinitas.sudoku.services;

import com.affinitas.sudoku.Exception.SudokuException;
import com.affinitas.sudoku.dto.SudokuMove;
import com.affinitas.sudoku.dto.ValidationResult;
import com.affinitas.sudoku.util.SudokuMoveValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by ganeshnagarajan on 12/17/16.
 *
 *  Service class to load the default board & validate the sudoku move
 */
@Service
public class SudokuServiceImpl implements SudokuService {

    private static final Logger log = LoggerFactory.getLogger(SudokuMoveValidator.class);

    public static final int[][] SUDOKU_BOARD = new int[][]{
            {7,0,0,0,4,0,5,3,0},
            {0,0,5,0,0,8,0,1,0},
            {0,0,8,5,0,9,0,4,0},
            {5,3,9,0,6,0,0,0,1},
            {0,0,0,0,1,0,0,0,5},
            {8,0,0,7,2,0,9,0,0},
            {9,0,7,4,0,0,0,0,0},
            {0,0,0,0,5,7,0,0,0},
            {6,0,0,0,0,0,0,5,0},
    };

    /**
     *  This method returns predefined static board values for every request
     * @return sudokuBoard
     */

    @Override
    public int[][] loadSudokuBoard() {
        return SUDOKU_BOARD;
    }

    /**
     * This method validates the sudoku move request and returns the validation result
     *
     * @param sudokuMove
     * @return validationResult
     */

    @Override
    public ValidationResult validateMove(SudokuMove sudokuMove) {
        ValidationResult result = new ValidationResult();
        try {
            int[][] move = sudokuMove.getSudokuBoard();
            move[sudokuMove.getMoveRow()][sudokuMove.getMoveColumn()] = sudokuMove.getMoveValue();
            result = new SudokuMoveValidator().validateSudokuMove(move);
        } catch (SudokuException e) {
            log.error("Sudoku Move failed validation ", e );
        }
        return result;
    }
}
