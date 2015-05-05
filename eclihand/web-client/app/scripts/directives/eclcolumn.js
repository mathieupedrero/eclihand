'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclColumn
 * @description
 * # eclColumn
 */
angular.module('webClientApp')
  .directive('eclColumn', function () {
    return {
      template: '<th>{{title}}</th>',
	  replace: true,
	  require: '^eclTable',
      restrict: 'E',
	  scope: {title: '=columnTitle'},
      link: function postLink(scope, element, attrs, eclTableController) {
        eclTableController.addColumn(scope);
      }
    };
  });
