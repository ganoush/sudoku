package com.affinitas.sudoku.dto;

/**
 * Created by ganeshnagarajan on 12/18/16.
 */
public enum GridType {
    ROW("row"),
    COLUMN("column"),
    BOX("3x3 box");

    private String type;

    GridType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
