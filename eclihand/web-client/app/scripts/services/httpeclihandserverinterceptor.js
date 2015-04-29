'use strict';

/**
 * @ngdoc service
 * @name webClientApp.httpEclihandServerInterceptor
 * @description
 * # httpEclihandServerInterceptor
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('httpEclihandServerInterceptor', ['authenticatedUser', 'requestUtils',
    function(authenticatedUser, requestUtils) {
		return {
			request: function(config) {
				if (authenticatedUser.getToken() != null && config['X-ecli-authMehod'] == requestUtils.authMethods.SESSION_TOKEN){
					return requestUtils.signRequest(config, authenticatedUser.getUser().login, authenticatedUser.getToken());
				}
				return config;
			}
		}
    }
  ]);
