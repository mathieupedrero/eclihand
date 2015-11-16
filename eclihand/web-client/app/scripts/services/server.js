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
      var onSuccess = function(listener) {
        return function(data, status, headers) {
          authenticatedUser.setToken(headers['X-session-id']);
            if (status==200){
                listener(data);
            }else if (status==204){
                listener();
            }
        }
      }
      
      var onError = function(listener){
          return function(data, status, headers, config, statusText) {
            if (status == 403) {
              authenticatedUser.clean();
            }
            if (status==500 && listener !=null){
                listener(data);
            }else{
                var title = {key:"common.http_error",params:{status:status}};
                var message = {key:"common.http_error",params:{status:status}};
                messageStack.addMessage(messageStack.Criticity.ERROR, title ,message);
            }
            throw headers;
          }
      }
      
      var processRequest = function(config, errorListener, listener) {
        return $http(config).success(onSuccess(listener)).error(onError(errorListener));
      }
      
      

      // Public API here
      return {
        login: function(login, password, successListener) {
          authenticatedUser.clean();
          var config = requestUtils.configBuilder()
            .defineAuthMethod(requestUtils.authMethods.NO_ONE)
            .defineMethod('GET')
            .defineUrl(serverUrl, '/eclihand-server/authentication/touch')
            .defineXEcliDate(new Date().toJSON()).build();
          var encodedPassword = CryptoJS.SHA256(CryptoJS.enc.Utf8.parse(login).concat(CryptoJS.SHA256(password))).toString(CryptoJS.enc.Base64);
		  
          requestUtils.signRequest(config, login, encodedPassword);

          processRequest(config, null, successListener);
        },
		
        createAccount: function(login, password, mailAddress, successListener) {
          var encodedPassword = CryptoJS.SHA256(CryptoJS.enc.Utf8.parse(login).concat(CryptoJS.SHA256(password))).toString(CryptoJS.enc.Base64);
		  
		  var userCreationRequest = {
			userToCreate:{
				login:login,
				mailAddress:mailAddress,
			},
			passwordToken:encodedPassword
		  };
		  
          var config = requestUtils.configBuilder()
            .defineAuthMethod(requestUtils.authMethods.NO_ONE)
            .defineMethod('POST')
            .defineData(userCreationRequest)
            .defineUrl(serverUrl, '/eclihand-server/user/create')
            .defineXEcliDate(new Date().toJSON()).build();
          requestUtils.signRequest(config, login, encodedPassword);

          processRequest(config, null, successListener);
        }
      };
    }
  ]);
