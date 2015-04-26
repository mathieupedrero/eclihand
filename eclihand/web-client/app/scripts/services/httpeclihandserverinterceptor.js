'use strict';

/**
 * @ngdoc service
 * @name webClientApp.httpEclihandServerInterceptor
 * @description
 * # httpEclihandServerInterceptor
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('httpEclihandServerInterceptor', ['$rootScope', 'requestUtils',
    function($rootScope, requestUtils) {
      return {
        request: function(request) {
          if (request.headers["Authentication"] == null && $rootScope.userName != null && $rootScope.token) {
            return requestUtils.signRequest(request, $rootScope.userName, $rootScope.token);
          }
          return request;
        }
      }
    }
  ]);
