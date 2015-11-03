'use strict';

/**
 * @ngdoc service
 * @name webClientApp.server
 * @description
 * # server
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('server', ['requestUtils', '$http', 'authenticatedUser', 'serverUrl', 'messageStack',
    function(requestUtils, $http, authenticatedUser, serverUrl, messageStack) {
      var onSuccess = function(dataProcessor) {
        return function(data, status, headers) {
          authenticatedUser.setToken(headers['X-session-id']);
          dataProcessor(data);
        }
      }
      var onError = function(data, status, headers, config, statusText) {
        console.log(status);
        if (status == 403) {
          authenticatedUser.clean();
        }
		var title = {key:"common.http_error",params:{status:status}};
		var message = {key:"common.http_error",params:{status:status}};
        messageStack.addMessage(messageStack.Criticity.ERROR, title ,message);
        throw headers;
      }
      var processDataRequest = function(config, dataProcessor) {
        return $http(config).success(onSuccess(dataProcessor)).error(onError);
      }
      var processVoidRequest = function(config) {
        $http(config).success(onSuccess).error(onError);
      }

      // Public API here
      return {
        login: function(login, password, dataProcessor) {
          authenticatedUser.clean();
          var config = requestUtils.configBuilder()
            .defineAuthMethod(requestUtils.authMethods.NO_ONE)
            .defineMethod('GET')
            .defineUrl(serverUrl, '/eclihand-server/authentication/touch')
            .defineXEcliDate(new Date().toJSON()).build();
          var encodedPassword = CryptoJS.SHA256(CryptoJS.enc.Utf8.parse(login).concat(CryptoJS.SHA256(password))).toString(CryptoJS.enc.Base64);
          requestUtils.signRequest(config, login, encodedPassword);

          processDataRequest(config, dataProcessor);
        },
      };
    }
  ]);
