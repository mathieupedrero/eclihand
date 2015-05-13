'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclValidatedFormGroup
 * @description
 * # eclValidatedFormGroup
 */
angular.module('webClientApp')
  .directive('eclValidatedFormGroup', ['eclUtils', '$compile',function (eclUtils, $compile) {
    return {
      restrict: 'E',
	  transclude:true,
      replace: true,
	  template: function (elem, attr){return '<div class="form-group" ng-class="('+attr['eclFormGroupErrorList']+'.length>0) ? \'has-error\' : \'\'" ecl-list-tool-tip="'+attr['eclFormGroupErrorList']+'" ng-transclude></div>';},
	  transclude:true,
      replace: true
    };
  }]);
