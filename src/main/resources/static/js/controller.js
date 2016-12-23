/**
 * Created by ganeshnagarajan on 12/19/16.
 */
Sudoku.controller('SudokuController', function SudokuController($scope, SudokuService) {
    $scope.sudokuBoard = [[]];
    $scope.initialLoad = [[]];
    $scope.messages = '';//ng-disabled="initialLoad[$parent.$index][$index] != 0"
    $scope.loadBoard = function(){
        SudokuService.loadBoard().success(function(response){
            $scope.initialLoad = JSON.parse(JSON.stringify(response.sudokuBoard));
            $scope.sudokuBoard = response.sudokuBoard;
            $scope.messages = '';
        });
    };
    $scope.validateMove = function(row, cols, value){
        if (value && !isNaN(value) && angular.isNumber(+value) && value <= 9 && value >= 0) {
            $scope.messages = [];
            var sudokueMoveRequest = { sudokuBoard: $scope.sudokuBoard, moveRow: row, moveColumn: cols, moveValue: value };
            SudokuService.validateMove(sudokueMoveRequest).success(function(response){
                $scope.messages = response.validationMessages;
                if (response.sudokuComplete) {
                    $scope.messages = ['Sudoku Completed'];
                }
            });
        } else {
            $scope.messages = ['Enter a valid value at row ' + row + ' column ' + cols];
        }
    };
});