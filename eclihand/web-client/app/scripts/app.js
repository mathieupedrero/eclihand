'use strict';

/**
 * @ngdoc overview
 * @name webClientApp
 * @description
 * # webClientApp
 *
 * Main module of the application.
 */
angular
  .module('webClientApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
	'pascalprecht.translate'
  ])
  .config(['$routeProvider', '$httpProvider', '$translateProvider', 'frMessages', 'enMessages',
    function($routeProvider, $httpProvider, $translateProvider, frMessages, enMessages ) {
      $routeProvider
        .when('/', {
          templateUrl: 'views/main.html',
          controller: 'MainCtrl'
        })
        .when('/about', {
          templateUrl: 'views/about.html',
          controller: 'AboutCtrl'
        })
        .when('/login', {
          templateUrl: 'views/login.html',
          controller: 'LoginCtrl'
        })
        .otherwise({
          redirectTo: '/'
        });

		$httpProvider.interceptors.push('httpEclihandServerInterceptor');
	  
		$translateProvider.translations('en', enMessages);
		$translateProvider.translations('fr', frMessages);
        $translateProvider.useSanitizeValueStrategy('escape');
		
		$translateProvider.preferredLanguage('fr');
    }
  ]);
