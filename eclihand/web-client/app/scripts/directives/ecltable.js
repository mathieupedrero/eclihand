'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclTable
 * @description
 * # eclTable
 */
angular.module('webClientApp')
  .directive('eclTable', function() {
    return {
      templateUrl: 'views/data-table.html',
      replace: true,
      transclude: true,
      restrict: 'E',
      scope: {
        id: '=id',
        source: '=tableSource',
        message: '=tableMessage',
        sizeChoices: '=?tableSizeChoices',
        pageSize: '=?tablePageSize',
        shouldPaginate: '=?tableShouldPaginate'
      },

      controller: ['$scope','$filter',
        function($scope,$filter) {
		  var firstPage = 1;
          $scope.columns = [];
          $scope.displayedData = [];
          $scope.page = 1;
		  if ($scope.sizeChoices == null){
			$scope.sizeChoices = [5, 10, 20];
		  }
		  if ($scope.pageSize == null){
			$scope.pageSize = 10;
		  }
          $scope.translationData = {
            pageSize: $scope.pageSize
          };
		  if ($scope.shouldPaginate == null){
			$scope.shouldPaginate = false;
		  }

          var feedLastPage = function() {
            $scope.lastPage = Math.max(0, 1 + Math.floor(($scope.source.length - 1) / $scope.pageSize));

            console.log('last page is : ' + $scope.lastPage);
          };

          var computeDirectPaginationButtons = function() {
            var pages = [];
            var surroundingButtonsNumber = 2;
			var totalPagesDisplayed = 2*surroundingButtonsNumber+1;
			var firstLinkPage;
			var lastLinkPage;
			if ($scope.lastPage<totalPagesDisplayed){
				firstLinkPage = firstPage;
				lastLinkPage = $scope.lastPage;
			}else if ($scope.page - firstPage < surroundingButtonsNumber){
				firstLinkPage = firstPage;
				lastLinkPage = firstPage + totalPagesDisplayed - 1;
			}else if ($scope.lastPage - $scope.page < surroundingButtonsNumber){
				firstLinkPage = $scope.lastPage - (totalPagesDisplayed - 1);
				lastLinkPage = $scope.lastPage;
			}else{
				firstLinkPage = $scope.page - surroundingButtonsNumber;
				lastLinkPage = $scope.page + surroundingButtonsNumber;
			}
            for (var i = firstLinkPage; i <= lastLinkPage; i++) {
              pages.push(i);
            }
            $scope.directPaginationButtons = pages;
          };
		  
		  var updateDisplayedData = function(){
			if ($scope.shouldPaginate){
				console.log('paginating');
				$scope.displayedData = $filter('pagination')($scope.source,{page:$scope.page,pageSize:$scope.pageSize});
			}else{
				$scope.displayedData = $scope.source;
			}
		  }

          $scope.selectPage = function(selectedPage) {
			feedLastPage();
			if (selectedPage >= firstPage && selectedPage <= $scope.lastPage){
				$scope.page = selectedPage;
			}else{
				$scope.page = firstPage;
			}
			computeDirectPaginationButtons();
			updateDisplayedData();
          }
		  
		  if ($scope.shouldPaginate){
			$scope.selectPage(1);
		  }else{
			$scope.displayedData = $scope.source;
		  }

          this.addColumn = function(column) {
            $scope.columns.push(column);
          };

          $scope.selectPageSize = function(pageSize) {
            $scope.pageSize = pageSize;
            $scope.translationData.pageSize = pageSize;
			$scope.selectPage($scope.page);
          };
		  
        }
      ]
    };
  });
