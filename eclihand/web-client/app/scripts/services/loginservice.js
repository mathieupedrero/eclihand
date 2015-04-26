'use strict';

/**
 * @ngdoc service
 * @name webClientApp.loginService
 * @description
 * # loginService
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('loginService', ['$http', 'requestUtils',
    function loginService($http, requestUtils) {
      function appendTransform(defaults, transform) {

        // We can't guarantee that the default transformation is an array
        defaults = angular.isArray(defaults) ? defaults : [defaults];

        // Append the new transformation to the defaults
        return defaults.concat(transform);
      }

      // Public API here
      return {

        login: function(login, password) {

          var encodedPassword = CryptoJS.SHA256(CryptoJS.enc.Utf8.parse(login).concat(CryptoJS.SHA256(password))).toString(CryptoJS.enc.Base64);

          var request = {
            method: 'GET',
            url: 'http://localhost/eclihand-server/authentication/touch',
            transformRequest: appendTransform($http.defaults.transformResponse, function(value) {
              return requestUtils.signRequest(value, login, encodedPassword);
            })
          }

          return $http(request).success(function(data, headers) {
            console.log('data[' + data + '],headers[' + headers + ']')
          }).error(function() {
            console.log('erreur de login de [' + login + '], password[' + password + '], ' + 'headers[' + request.headers.Authorization + ']')
          });
        }


      };
    }
  ]);
