/**
 * Created by ganeshnagarajan on 12/19/16.
 */
var SudokuServicesModule = angular.module('SudokuService',[]);

SudokuServicesModule.service('SudokuService',['$http', function($http) {
    'use strict';
    this.loadBoard = function() {
        return $http.get('sudoku/loadBoard');
    };
    this.validateMove = function(sudokuMoveRequest) {
        return $http.put('sudoku/validateMove', sudokuMoveRequest);
    };
}]);