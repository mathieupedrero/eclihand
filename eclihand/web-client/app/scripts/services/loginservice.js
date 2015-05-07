'use strict';

/**
 * @ngdoc service
 * @name webClientApp.loginService
 * @description
 * # loginService
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('loginService', ['server',
    function(server) {

      return {
        login: function(login, password) {
			var loggedIn = server.login(login, password);
        }
      };
    }
  ]);
