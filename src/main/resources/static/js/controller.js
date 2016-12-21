/**
 * Created by ganeshnagarajan on 12/19/16.
 */
Sudoku.controller('SudokuController', function SudokuController($scope, SudokuService) {
    $scope.sudokuBoard = '';
    $scope.messages = '';
    $scope.loadBoard = function(){
        SudokuService.loadBoard().success(function(response){
            $scope.sudokuBoard = response.sudokuBoard;
            //$scope.messages = 'Sudoku Board loaded successfully!';
        });
    };
    $scope.validateMove = function(row, cols, value){
        //console.log(value);
        //Invoke the put service to validate move and shovel the error messages to messages scope
    };
});