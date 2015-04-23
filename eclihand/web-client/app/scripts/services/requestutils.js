'use strict';

/**
 * @ngdoc service
 * @name webClientApp.RequestUtils
 * @description
 * # RequestUtils
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('requestUtils', function () {
    // Service logic
    // ...

    var meaningOfLife = 42;

    // Public API here
    return {
      signRequest: function (request, userName, token) {
				var separator = '\n';
				var method = request.method;
				var content = '';
				var contentType = '';
				if (request.content != null){
					content = CryptoJS.SHA1(request.content);
				}
				if (request.contentType != null){
					contentType = request.contentType;
				}
				
				var date = angular.toJson(new Date());
				var uri = request.uri;
				
				console.log(method);
				console.log(content);
				console.log(contentType);
				console.log(date);
				console.log(uri);
				
                var message = method + separator + content + separator + contentType + separator + date + separator + uri;
				var signature = CryptoJS.HmacSHA256(message,token);
				
                request.headers["Authentication"] = userName+":"+signature;
                request.headers["X-ecli-Date"] = date;
				
				return request;
      }
    };
  });
