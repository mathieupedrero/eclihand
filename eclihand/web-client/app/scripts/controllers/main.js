'use strict';

/**
 * @ngdoc function
 * @name webClientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the webClientApp
 */
angular.module('webClientApp')
  .controller('MainCtrl', ['$scope', 'loginService', 'authenticatedUser',
    function($scope, loginService, authenticatedUser) {
      console.log('Main control log');
      console.log(loginService.login);
      $scope.login = "Mon Login";
      $scope.password = "Mon Password";
      $scope.authenticatedUser = authenticatedUser.getUser;
      $scope.userLogin = authenticatedUser.getUserName;
      $scope.onLogin = function() {
        loginService.login($scope.login, $scope.password);
      };
      $scope.translationData = function() {
        return {
          userName: $scope.userLogin()
        };
      }

      $scope.myData = [{
        name: "Mathieu",
        number: 1
      }, {
        name: "Mathieu",
        number: 2
      }, {
        name: "Mathieu",
        number: 3
      }, {
        name: "Mathieu",
        number: 4
      }, {
        name: "Mathieu",
        number: 5
      }, {
        name: "Mathieu",
        number: 6
      }, {
        name: "Mathieu",
        number: 7
      }, {
        name: "Mathieu",
        number: 8
      }, {
        name: "Mathieu",
        number: 9
      }, {
        name: "Mathieu",
        number: 10
      }, {
        name: "Mathieu",
        number: 11
      }, {
        name: "Mathieu",
        number: 12
      }, {
        name: "Mathieu",
        number: 13
      }, {
        name: "Mathieu",
        number: 14
      }, {
        name: "Mathieu",
        number: 15
      }, {
        name: "Mathieu",
        number: 16
      }, {
        name: "Mathieu",
        number: 17
      }, {
        name: "Mathieu",
        number: 18
      }, {
        name: "Mathieu",
        number: 19
      }];
    }
  ]);
