package com.affinitas.sudoku.controller;

import com.affinitas.sudoku.dto.SudokuMove;
import com.affinitas.sudoku.dto.ValidationResult;
import com.affinitas.sudoku.dto.request.SudokuMoveRequest;
import com.affinitas.sudoku.dto.response.SudokuLoadResponse;
import com.affinitas.sudoku.dto.response.SudokuMoveResponse;
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
    public SudokuLoadResponse loadSudokuBoard(){
        log.info("Request to load default sudoku board");

        int[][] loadResponse = sudokuServiceImpl.loadSudokuBoard();

        //Set the response in the response object.
        SudokuLoadResponse response = new SudokuLoadResponse();
        response.setSudokuBoard(loadResponse);
        log.info("Returning the default sudoku board " + response);

        return response;
    }

    /**
     *  This is the method to validate each sudoku move.
     *  This also tells if the move results in board completion.
     * @param request
     * @return sudokuMoveResponse
     */

    @RequestMapping(value = "/validateMove",  method = RequestMethod.PUT)
    public SudokuMoveResponse validateSudokuMove(@RequestBody SudokuMoveRequest request){
        log.info("Request to validate Sudoku Move " + request);

        ValidationResult result = sudokuServiceImpl.validateMove(new SudokuMove(request.getSudokuBoard(),request.getMoveRow(), request.getMoveColumn(), request.getMoveValue()));
        SudokuMoveResponse response = new SudokuMoveResponse();
        response.setSudokuComplete(result.isSudokuComplete());
        response.setValidMove(result.isValidMove());

        log.info("Response to validate Sudoku Move " + response);
        return response;
    }
}
