'use strict';

/**
 * @ngdoc function
 * @name webClientApp.controller:MessagestackCtrl
 * @description
 * # MessagestackCtrl
 * Controller of the webClientApp
 */
angular.module('webClientApp')
  .controller('MessagestackCtrl', function ($scope) {
    $scope.messages = [];
	
	var Criticity = {
		INFO : 0,
		WARN : 1,
		ERROR : 2
	};
	
	var addMessage = function(criticity,message){
		$scope.messages.push({
			criticity:criticity,
			message:message
		})
	};
	
	var removeMessage = function(index){
		$scope.messages.splice(index,1);
	};
  });
