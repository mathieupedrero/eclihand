'use strict';

/**
 * @ngdoc service
 * @name webClientApp.loginService
 * @description
 * # loginService
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('loginService', ['$http','requestUtils',function loginService($http,requestUtils) {
    // Public API here
    return {
	
      login: function(login, password) {
	  
	  var encodedPassword = btoa(CryptoJS.SHA256(login + CryptoJS.SHA256(password)));
		
        var request = {
			method: 'GET',
			url: 'http://localhost/eclihand-server/authentication/touch',
			transformResponse : function(value) {
				return requestUtils.signRequest(value,login,encodedPassword);
			}
        }

        return $http(request).success(function(data, headers) {
          console.log('data[' + data + '],headers[' + headers + ']')
        }).error(function() {
          console.log('erreur de login de [' + login + '], password[' + password + '], ' + 'headers[' + request.headers.Authorization + ']')
        });
      }
	  
	  
    };
  }]);
