package com.affinitas.sudoku.services;

import com.affinitas.sudoku.dto.SudokuMove;
import com.affinitas.sudoku.dto.ValidationResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static com.affinitas.sudoku.SudokuTestUtils.SUDOKU_ONE_MOVE_FINISH;
import static org.junit.Assert.assertEquals;

/**
 * Created by ganeshnagarajan on 1/18/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SudokuServiceTest {

    @InjectMocks
    SudokuServiceImpl service;

    @Test
    public void shouldLoadBoard(){
        int[][] sudokuBoard = service.loadSudokuBoard();
        assertEquals(Arrays.deepToString(SudokuServiceImpl.SUDOKU_BOARD) , Arrays.deepToString(sudokuBoard));
    }

    @Test
    public void shouldValidateMove(){
        SudokuMove move = new SudokuMove();
        move.setMoveValue(5);
        move.setMoveRow(0);
        move.setMoveColumn(0);
        move.setSudokuBoard(SUDOKU_ONE_MOVE_FINISH);
        ValidationResult result = service.validateMove(move);
        assertEquals(true, result.isSudokuComplete());
        assertEquals(true, result.isValidMove());
        assertEquals(0, result.getValidationMessages().size());
    }
}
