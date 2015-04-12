'use strict';

/**
 * @ngdoc function
 * @name webClientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the webClientApp
 */
angular.module('webClientApp')
  .controller('MainCtrl', function($scope, loginService) {
    console.log('Main control log');
    console.log(loginService.login);
    $scope.login = "Mon Login";
    $scope.password = "Mon Password";
    $scope.onLogin = function() {
      loginService.login($scope.login, $scope.password);
    };
  });
