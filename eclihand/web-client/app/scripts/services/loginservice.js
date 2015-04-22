'use strict';

/**
 * @ngdoc service
 * @name webClientApp.loginService
 * @description
 * # loginService
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('loginService', function loginService($http) {
    // Public API here
    return {
      login: function(login, password) {
        var signature = login + ':' + btoa(CryptoJS.SHA1(CryptoJS.SHA1(password)));
        var request = {
          method: 'GET',
          url: 'http://localhost/eclihand-server/authentication/touch',
          headers: {
            'Authorization': 'Basic ' + signature,
			'X-ecli-Date' : new Date().toJSON()
          }
        }

        return $http(request).success(function(data, headers) {
          console.log('data[' + data + '],headers[' + headers + ']')
        }).error(function() {
          console.log('erreur de login de [' + login + '], password[' + password + '], ' + 'headers[' + request.headers.Authorization + ']')
        });
      }
	  
	  
    };
  });
