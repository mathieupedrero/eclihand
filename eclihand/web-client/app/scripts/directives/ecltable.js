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
        message: '=tableMessage'
      },

      controller: ['$scope',
        function($scope) {
          $scope.columns = [];
          $scope.page = 1;
          $scope.pageSize = 10;
          $scope.translationData = {
            pageSize: $scope.pageSize
          };
          $scope.pageSizeChoices = [5, 10, 20];

          var feedLastPage = function() {
            $scope.lastPage = Math.max(0, 1 + Math.floor(($scope.source.length - 1) / $scope.pageSize));

            console.log('last page is : ' + $scope.lastPage);
          };

          feedLastPage();

          var computeDirectPaginationButtons = function() {
            var pages = [];
            var surroundingButtonsNumber = 2;
            for (var i = Math.max(1, $scope.page - surroundingButtonsNumber); i <= Math.min($scope.lastPage, $scope.page + surroundingButtonsNumber); i++) {
              pages.push(i);
            }
            $scope.directPaginationButtons = pages;
          };

          computeDirectPaginationButtons();

          $scope.selectPage = function(selectedPage) {
            $scope.page = selectedPage;
            feedLastPage();
            computeDirectPaginationButtons();
          }

          this.addColumn = function(column) {
            $scope.columns.push(column);
          };

          $scope.selectPageSize = function(pageSize) {
            $scope.pageSize = pageSize;
            $scope.translationData.pageSize = pageSize;
          };
        }
      ]
    };
  });
