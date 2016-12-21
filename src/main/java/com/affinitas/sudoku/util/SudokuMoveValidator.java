package com.affinitas.sudoku.util;

import com.affinitas.sudoku.Exception.SudokuException;
import com.affinitas.sudoku.dto.GridType;
import com.affinitas.sudoku.dto.SudokuMove;
import com.affinitas.sudoku.dto.ValidationResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ganeshnagarajan on 12/18/16.
 *
 * Utility class to validate SudokuMove
 */
public class SudokuMoveValidator {

    private static final Logger log = LoggerFactory.getLogger(SudokuMoveValidator.class);

    /**
     * Method to validate the sudokuMove
     * @param sudokuBoard
     * @return validationResult
     */
    public ValidationResult validateSudokuMove(int[][] sudokuBoard) throws SudokuException {

        ValidationResult result = new ValidationResult();

        /* This indicator indicates whether board is completed or not. Value 27 (9 rows , 9 columns, 9 3x3 boxes) indicates board is complete with valid values*/
        int validationCount = 0;
        List<String> validationMessages = new ArrayList<>();

        for (int i = 0; i < sudokuBoard.length; i++) {
             /*1D array for ith row*/
            int[] rowGrid = new int[sudokuBoard.length];
            for (int j = 0; j < sudokuBoard[i].length; j++) {
                if (!isValidNumber(sudokuBoard[i][j])) {
                    log.error("Invalid sudoku entry at Row: " + i + " , Column: " + j + " , value: " + sudokuBoard[i][j]);
                    throw new SudokuException("Invalid sudoku entry at Row: " + i + " , Column: " + j + " , value: " + sudokuBoard[i][j]);
                }
                rowGrid[j] = sudokuBoard[j][i];
            }

            validationCount = validationCount + validateGrid(sudokuBoard[i], GridType.ROW, i, validationMessages);
            /*Validate the i th column*/
            validationCount = validationCount + validateGrid(rowGrid, GridType.COLUMN, i, validationMessages);
        }

        /*Validate 3x3 grid here*/
        int boxCount = 0; int row = 0; int column = 0; int count = 0;
        int[] grid = new int[sudokuBoard.length];
        while (boxCount < sudokuBoard.length){
            grid[count] = sudokuBoard[row][column];
            column++;
            if (column%3 == 0) {
                column = column - 3;
                row++;
            }
            count++;
            if (count == sudokuBoard.length) {
                validationCount = validationCount + validateGrid(grid, GridType.BOX, boxCount, validationMessages);
                boxCount++;
                row = row -3;
                column = column + 3;
                count = 0;
            }
            if (boxCount != 0 && count == 0 && boxCount%3 == 0){
                row = row +3;
                column = 0;
            }
        }

        result.setValidationMessages(validationMessages);
        result.setValidMove(validationMessages.size() == 0);
        result.setSudokuComplete(validationCount == 27 && validationMessages.size() == 0);
        return result;
    }

    /**
     *  Method to validate each row/column
     * @param grid
     * @return validatonCount
     */
    private int validateGrid(int[] grid, GridType type, int order,List<String> validationMessages){

        /*
        *  Validate for uniqueness for each row/column/3x3 box.
        *  After removing zeroes, the distinct count must be less than or equal to 9.
        *  distinct count 9 indicates the column is filled with distinct values from 1 to 9
        *  Sum of Number of zeroes count & non zero distinct count should equal to 9 otherwise duplicate entry present.
        * */

        int validationCount = 0;
        long distinctRowCount = Arrays.stream(grid).parallel().filter(k -> k!= 0).distinct().count();
        long zeroesCount = Arrays.stream(grid).parallel().filter(k -> k== 0).count();
        if ((zeroesCount + distinctRowCount) != 9){
            log.error("Duplicate Entry present in the " + type.getType() + order);
            validationMessages.add("Duplicate Entry present in the " + type.getType() + order);
        }

        /* Sum of the column equals 45 indicates the grid is completed with values*/
        if (Arrays.stream(grid).sum() == 45){
            log.info("Sudoku completed for the " + type.getType() + order);
            validationCount++;
        }
        return validationCount;
    }

    /**
     * Validate if the numbers are in the range 0 - 9
     * @param number
     * @return
     */
    private boolean isValidNumber(int number){
        return number >=0 && number <= 9;
    }
}
