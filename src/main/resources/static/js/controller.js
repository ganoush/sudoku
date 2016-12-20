/**
 * Created by ganeshnagarajan on 12/19/16.
 */
Sudoku.controller('SudokuController', function SudokuController($scope, SudokuService) {
    $scope.sudokuBoard = {};
    $scope.messages = {};
    SudokuService.loadBoard().success(function(response){
        $scope.sudokuBoard = response.sudokuBoard;
        $scope.messages = 'Sudoku Board loaded successfully!';
    });
});