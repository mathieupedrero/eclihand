'use strict';

/**
 * @ngdoc service
 * @name webClientApp.eclUtils
 * @description
 * # eclUtils
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('eclUtils', function () {
    return {
      isNullOrEmpty: function (array) {
        return array == null || array.length==0;
      }
    };
  });
