'use strict';

/**
 * @ngdoc function
 * @name webClientApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the webClientApp
 */
angular.module('webClientApp')
  .controller('RegistrationCtrl', ['$scope','server',function ($scope,server) {
      $scope.data={};
      $scope.data.login='';
      $scope.data.password='';
      $scope.data.mailAddress='';
      $scope.data.loginFormSubmitted=false;
	  
        
      $scope.register = function(isValid) {
		this.data.loginFormSubmitted=true;
		if (isValid){
            console.log('Loggin form validated. Going to log in...');
			server.createAccount(this.data.login, this.data.password, this.data.mailAddress, null);
		}
      };
  }]);
