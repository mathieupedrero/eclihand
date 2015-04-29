'use strict';

/**
 * @ngdoc service
 * @name webClientApp.authenticatedUser
 * @description
 * # authenticatedUser
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('authenticatedUser', function () {
	var user = null;
	var token = null;

    // Public API here
    return {
		setUser: function (newUser) {
			user = newUser;
		},
		getUser: function () {
			return newUser;
		},
		  
		setToken: function (newToken) {
			token = newToken;
		},
		getToken: function () {
			return token;
		},
		  
		clean: function() {
			token=null;
			user=null;
		}
  }});
