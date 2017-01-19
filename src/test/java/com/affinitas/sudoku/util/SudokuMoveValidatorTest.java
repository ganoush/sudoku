package com.affinitas.sudoku.util;

import com.affinitas.sudoku.Exception.SudokuException;
import com.affinitas.sudoku.dto.ValidationResult;
import org.junit.Before;
import org.junit.Test;

import static com.affinitas.sudoku.SudokuTestUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by ganeshnagarajan on 1/18/17.
 */
public class SudokuMoveValidatorTest {

    SudokuMoveValidator validator;

    @Before
    public void setUp(){
        validator = new SudokuMoveValidator();
    }

    @Test
    public void shouldValidateSudokuMove() throws SudokuException {
        ValidationResult result = validator.validateSudokuMove(SUDOKU_INVALID_MOVE);
        assertEquals(false, result.isSudokuComplete());
        assertEquals(false, result.isValidMove());
        assertEquals(3, result.getValidationMessages().size());
    }

    @Test
    public void shouldValidateSudokuMoveCompletion() throws SudokuException {
        ValidationResult result = validator.validateSudokuMove(SUDOKU_COMPLETION);
        assertEquals(true, result.isSudokuComplete());
        assertEquals(true, result.isValidMove());
        assertEquals(0, result.getValidationMessages().size());
    }

    @Test(expected = SudokuException.class)
    public void shouldValidateInvalidNumbers() throws SudokuException {
        validator.validateSudokuMove(SUDOKU_INVALID_NUMBER);
    }
}
