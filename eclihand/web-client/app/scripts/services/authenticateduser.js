'use strict';

/**
 * @ngdoc service
 * @name webClientApp.authenticatedUser
 * @description
 * # authenticatedUser
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('authenticatedUser', function() {
    var user;
    var token;

    // Public API here
    return {
      setUser: function(newUser) {
        user = newUser;
      },
      getUser: function() {
        return user;
      },
      getUserName: function() {
        return user != null ? user.login : null;
      },

      setToken: function(newToken) {
        token = newToken;
      },
      getToken: function() {
        return token;
      },

      clean: function() {
        token = null;
        user = null;
      },

      isAuthenticated: function() {
        return user != null;
      }
    }
  });
