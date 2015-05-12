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
	  template: '<div class="form-group"><div ecl-list-tool-tip="errorList" ng-transclude></div></div>',
	  transclude:true,
      replace: true,
	  scope: {errorList:'=eclFormGroupErrorList'},
	  compile : function compile(element, attrs) {
		return {
          pre: function preLink(scope, iElement, iAttrs, controller) { 
			var updateFormGroup = function(newVal, oldVal){
				var emptyNew = eclUtils.isNullOrEmpty(newVal); 
				var emptyOld = eclUtils.isNullOrEmpty(oldVal); 
				if (emptyNew && !emptyOld){
					element.removeClass('has-error');
				}else if (!emptyNew && emptyOld){
					element.addClass('has-error');
				}
			};
			updateFormGroup(scope.errorList,null);
			scope.$watch('errorList',updateFormGroup);
		  },
          post: function postLink(scope, iElement, iAttrs, controller) {  
          }
		}
	  }
    };
  }]);
