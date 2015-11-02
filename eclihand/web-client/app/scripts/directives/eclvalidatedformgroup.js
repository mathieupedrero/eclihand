'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclValidatedFormGroup
 * @description
 * # eclValidatedFormGroup
 */
angular.module('webClientApp')
  .directive('eclValidatedFormGroup', ['eclUtils', '$compile',
    function(eclUtils, $compile) {
      return {
        restrict: 'E',
        scope: {
            errorList: '=eclFormGroupErrorList'
          },
        replace: true,
        transclude: true,
        templateUrl: 'views/validated-form-group.html'
      };
    }
  ]);
