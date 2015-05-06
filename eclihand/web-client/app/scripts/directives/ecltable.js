'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclTable
 * @description
 * # eclTable
 */
angular.module('webClientApp')
  .directive('eclTable', function () {
    return {
		templateUrl: 'views/data-table.html',
		replace: true,
        transclude: true,
		restrict: 'E',
		scope: {id: '=id', source: '=tableSource', message: '=tableMessage'},
		
        controller: ['$scope',function($scope){
			$scope.columns = [];
			var totalNumber;
			$scope.page = 1;
			$scope.pageSize = 10;
			$scope.pageSizeChoices = [5,10,20];
			
			$scope.translationData={pageSize:$scope.pageSize};
			
			this.addColumn = function(column){
				$scope.columns.push(column);
			};
			
			this.lastPage = function(){
				return $scope.source.length;
			};
				
			$scope.selectPageSize = function(pageSize){
				$scope.pageSize = pageSize;
				$scope.translationData.pageSize = pageSize;
            };
				
			$scope.directPaginationButtons = function(currentPage){
				var pages = [];
				var surroundingButtonsNumber = 2;
				for (i=Math.max(1,currentPage-2);i<=Math.min()
            };
        }]
    };
  });
