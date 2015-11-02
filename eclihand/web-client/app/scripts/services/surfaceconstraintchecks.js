'use strict';

/**
 * @ngdoc service
 * @name webClientApp.surfaceConstraintChecks
 * @description
 * # surfaceConstraintChecks
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('surfaceConstraintChecks', ['eclUtils',function (eclUtils) {
    return {
      createField: function (fieldValue) {
		return {
		  value:fieldValue,
		  errorList:[]
		};
      },
      mandatoryField: function (field) {
	  console.log('value [' + field.value + '] checked');
		if (eclUtils.isNullOrEmptyString(field.value)){
			field.errorList.push('common.mandatory_field');
			return false;
		}
		return true;
      }
    };
  }]);
