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
		template: '<table class="table table-stripped table-condensed">' +
					'<thead>' +
						'<tr ng-transclude>' +
						'</tr>' +
					'</thead>' +
					'<tbody>' +
						'<tr ng-repeat="element in elements | pagination:page:pageSize">' +
							'<th ng-repeat="column in comumns">' +
								'{{element[column.title]}}' +
							'</th>' +
						'</tr>' +
					'</tbody>' +
				'</table>' +
				'ma var = {{$scope.bonjourVar}}'+
				'<button type="button" class="btn btn-default" ng-click="refresh">{{"common.refresh" | translate}}</button>',
        transclude: true,
		restrict: 'E',
		scope: {source: '=tableSource'},
		
        controller: ['$scope',function($scope){
            var columns = [];
            var elements = [];
            var totalNumber;
			this.page = 1;
			this.pageSize = 10;
			$scope.bonjourVar = 'Baonjour !!';
			
            this.addColumn = function(column){
                columns.push(column);
            };
            this.getPage = function(page){
                columns.push(column);
            };
            $scope.refresh = function(){
				$scope.bonjourVar = 'Baonjour !!!';
				console.log($scope.source);
				elements = $scope.source;
            };
        }]
    };
  });
