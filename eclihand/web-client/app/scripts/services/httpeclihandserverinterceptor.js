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
		request: function(config) {
			if (config['X-ecli-authMehod'] == requestUtils.authMethods.SESSION_TOKEN){
				return requestUtils.signRequest(config, $rootScope.userName, $rootScope.token);
			}
			return config;
        }
      }
    }
  ]);
