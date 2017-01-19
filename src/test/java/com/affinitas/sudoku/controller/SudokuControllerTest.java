package com.affinitas.sudoku.controller;

import com.affinitas.sudoku.SudokuApplicationRunner;
import com.affinitas.sudoku.SudokuTestUtils;
import com.affinitas.sudoku.dto.request.SudokuMoveRequest;
import com.affinitas.sudoku.services.SudokuServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ganeshnagarajan on 12/18/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SudokuApplicationRunner.class)
@WebAppConfiguration
public class SudokuControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldLoadTable() throws Exception{
        this.mockMvc.perform(get("/sudoku/loadBoard"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sudokuBoard").isArray());
    }

    @Test
    public void shouldTestValidMove() throws Exception{
        /*Create a dummy request */
        SudokuMoveRequest request = new SudokuMoveRequest();
        request.setSudokuBoard(SudokuServiceImpl.SUDOKU_BOARD);
        request.setMoveColumn(1);
        request.setMoveRow(0);
        request.setMoveValue(1);
        this.mockMvc.perform(put("/sudoku/validateMove").contentType(SudokuTestUtils.SUDOKU_REQUEST_JSON_TYPE).content(SudokuTestUtils.asJsonString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sudokuComplete").value(false))
                .andExpect(jsonPath("$.validMove").value(true));
    }

    @Test
    public void shouldTestInvalidateMove() throws Exception{
        SudokuMoveRequest request = new SudokuMoveRequest();
        request.setSudokuBoard(SudokuServiceImpl.SUDOKU_BOARD);
        request.setMoveColumn(1);
        request.setMoveRow(0);
        request.setMoveValue(7);
        this.mockMvc.perform(put("/sudoku/validateMove").contentType(SudokuTestUtils.SUDOKU_REQUEST_JSON_TYPE).content(SudokuTestUtils.asJsonString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sudokuComplete").value(false))
                .andExpect(jsonPath("$.validMove").value(false));
    }

    @Test
    public void shouldValidateMoveCompletion() throws Exception{
        SudokuMoveRequest request = new SudokuMoveRequest();
        request.setSudokuBoard(SudokuTestUtils.SUDOKU_ONE_MOVE_FINISH);
        request.setMoveColumn(0);
        request.setMoveRow(0);
        request.setMoveValue(5);
        this.mockMvc.perform(put("/sudoku/validateMove").contentType(SudokuTestUtils.SUDOKU_REQUEST_JSON_TYPE).content(SudokuTestUtils.asJsonString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sudokuComplete").value(true))
                .andExpect(jsonPath("$.validMove").value(true));
    }
}
