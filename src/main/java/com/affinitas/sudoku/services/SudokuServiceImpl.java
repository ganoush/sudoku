package com.affinitas.sudoku.services;

import org.springframework.stereotype.Service;

/**
 * Created by ganeshnagarajan on 12/17/16.
 */
@Service
public class SudokuServiceImpl implements SudokuService {
    public int[][] loadSudokuBoard() {
        int[][] sudkuBoard = new int[8][8];
        return sudkuBoard;
    }
}
