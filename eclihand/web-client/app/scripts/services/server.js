'use strict';

/**
 * @ngdoc service
 * @name webClientApp.server
 * @description
 * # server
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('server', ['requestUtils','$http','$rootScope','MessagestackCtrl', function (requestUtils, $http, $rootScope,MessagestackCtrl) {
		var onSuccess = function(headers) {
			$rootScope.authenticatedUser.token = headers['X-session-id'];
		}
		var onError = function(data,status,headers) {
			console.log(status);
			if (status==403){
				$rootScope.authenticatedUser = undefined;
			}
			MessagestackCtrl.addMessage(MessagestackCtrl.Criticity.ERROR, 'Erreur HTTP '+status);
			throw headers;
		}
        var processDataRequest = function(config) {
			return $http(config).success(onSuccess).error(onError);
        }
        var processVoidRequest = function(config) {
			$http(config).success(onSuccess).error(onError);
        }

    // Public API here
    return {
        login: function(login, password) {
			$rootScope.authenticatedUser = {};
			var config = requestUtils.configBuilder()
						.defineAuthMethod(requestUtils.authMethods.NO_ONE)
						.defineMethod('GET')
						.defineUrl('http://localhost','/eclihand-server/authentication/touch')
						.defineXEcliDate(new Date().toJSON()).build();
			var encodedPassword = CryptoJS.SHA256(CryptoJS.enc.Utf8.parse(login).concat(CryptoJS.SHA256(password))).toString(CryptoJS.enc.Base64);
			requestUtils.signRequest(config, login, encodedPassword);

			$rootScope.authenticatedUser.user = processDataRequest(config);
        }
    };
  }]);
