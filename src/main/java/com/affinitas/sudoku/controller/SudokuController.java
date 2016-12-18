package com.affinitas.sudoku.controller;

import com.affinitas.sudoku.dto.response.SudokuLoadResponse;
import com.affinitas.sudoku.dto.response.SudokuResponse;
import com.affinitas.sudoku.services.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ganeshnagarajan on 12/17/16.
 */

@RestController
@RequestMapping(value="/sudoku")
public class SudokuController {

    @Autowired
    private SudokuService sudokuServiceImpl;

    @RequestMapping(value = "/loadBoard",  method = RequestMethod.GET)
    public SudokuLoadResponse loadSudokuBoard(){
        int[][] loadResponse = sudokuServiceImpl.loadSudokuBoard();

        //Set the response in the response object.
        SudokuLoadResponse response = new SudokuLoadResponse();
        response.setSudokuBoard(loadResponse);
        return response;
    }
}
