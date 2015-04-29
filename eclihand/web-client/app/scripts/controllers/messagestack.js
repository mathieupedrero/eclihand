'use strict';

/**
 * @ngdoc function
 * @name webClientApp.controller:MessagestackCtrl
 * @description
 * # MessagestackCtrl
 * Controller of the webClientApp
 */
angular.module('webClientApp')
  .controller('MessagestackCtrl', ['messageStack','$scope',function (messageStack,$scope) {
		$scope.messages = messageStack.getMessages();
		
		$scope.removeMessage = function(index){
			messageStack.removeMessage(index);
		};
  }]);
