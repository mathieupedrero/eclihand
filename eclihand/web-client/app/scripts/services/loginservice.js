'use strict';

/**
 * @ngdoc service
 * @name webClientApp.loginService
 * @description
 * # loginService
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('loginService', ['server', 'authenticatedUser',
    function(server, authenticatedUser) {

      return {
        login: function(login, password) {
          var loggedIn = server.login(login, password, function(user) {
            console.log(user);
            authenticatedUser.setUser(user);
          });
        }
      };
    }
  ]);
