describe('controller', function () {
    var $scope, SudokuService;

    beforeEach(module('Sudoku'));

    beforeEach(inject(function($injector){
        $scope = $injector.get('$rootScope').$new();
    }));

    var sudokuLoadResponse = {
        success: function (func) {
            func([[1,0],[2,1]]);
        }
    };

    var sudokuValidateMoveResponse = {
        success: function (func) {
            func({ validMove: false, sudokuComplete: false, validationMessages: ['Please enter a valid number']});
        }
    };

    var sudokuServiceMock = {
        loadBoard: function(){
            return sudokuLoadResponse;
        },
        validateMove: function(){
            return sudokuValidateMoveResponse;
        }
    };

    it('should load board', function(){
         inject(function($controller){
             $controller('SudokuController', { $scope: $scope, SudokuService: sudokuServiceMock } );
             $scope.loadBoard();
             expect($scope.sudokuBoard.length).toEqual(2);
         });
    });

    it('should validate move ', function(){
        inject(function($controller){
            $controller('SudokuController', { $scope: $scope, SudokuService: sudokuServiceMock } );
            $scope.validateMove(1,2,3);
            expect($scope.messages.length).toEqual(1);
            expect($scope.messages[0]).toEqual('Please enter a valid number');
        });
    });
});