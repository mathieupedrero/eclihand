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

      // Public API here
      return {
        login: function(login, password) {
			server.login(login, password);
        }
      };
    }
  ]);
