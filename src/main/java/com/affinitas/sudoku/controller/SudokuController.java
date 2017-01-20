package com.affinitas.sudoku.controller;

import com.affinitas.sudoku.dto.SudokuMove;
import com.affinitas.sudoku.dto.ValidationResult;
import com.affinitas.sudoku.services.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ganeshnagarajan on 12/17/16.
 *
 *  This is the Front Controller where the requests are intercepted.
 */

@RestController
@RequestMapping(value="/sudoku")
public class SudokuController {

    private static final Logger log = LoggerFactory.getLogger(SudokuController.class);

    @Autowired
    private SudokuService sudokuServiceImpl;

    /**
     *  This is the initial method to load the default sudoku board values
     * @return sudokuLoadResponse
     */

    @RequestMapping(value = "/loadBoard",  method = RequestMethod.GET)
    public int[][] loadSudokuBoard(){
        log.info("Request to load default sudoku board");
        int[][] loadResponse = sudokuServiceImpl.loadSudokuBoard();
        log.info("Returning the default sudoku board " + loadResponse);
        return loadResponse;
    }

    /**
     *  This is the method to validate each sudoku move.
     *  This also tells if the move results in board completion.
     * @param request
     * @return sudokuMoveResponse
     */

    @RequestMapping(value = "/validateMove",  method = RequestMethod.PUT)
    public ValidationResult validateSudokuMove(@RequestBody SudokuMove request){
        log.info("Request to validate Sudoku Move " + request);

        ValidationResult result = sudokuServiceImpl.validateMove(new SudokuMove(request.getSudokuBoard(),request.getMoveRow(), request.getMoveColumn(), request.getMoveValue()));

        log.info("Response to validate Sudoku Move " + result);
        return result;
    }
}
