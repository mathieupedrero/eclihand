'use strict';

/**
 * @ngdoc service
 * @name webClientApp.server
 * @description
 * # server
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('server', ['requestUtils','$http',function (requestUtils, $http) {

    // Public API here
    return {
        login: function(login, password) {
			var encodedPassword = CryptoJS.SHA256(CryptoJS.enc.Utf8.parse(login).concat(CryptoJS.SHA256(password))).toString(CryptoJS.enc.Base64);
			console.log(encodedPassword);

			var config = requestUtils.configBuilder()
						.defineAuthMethod(requestUtils.authMethods.NO_ONE)
						.defineMethod('GET')
						.defineContentType('application/json')
						.defineUrl('http://localhost','/eclihand-server/authentication/touch')
						.defineXEcliDate(new Date().toJSON()).build();
			
			requestUtils.signRequest(config, login, password);

			return $http(config).success(function(data, headers) {
				console.log('data[' + data + '],headers[' + headers + ']')
			}).error(function() {
				console.log('erreur de login de [' + login + '], password[' + password + '], ' + 'headers[' + config.headers.Authorization + ']')
			});
        }
    };
  }]);
