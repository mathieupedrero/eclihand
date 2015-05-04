'use strict';

/**
 * @ngdoc service
 * @name webClientApp.messages
 * @description
 * # messages
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('messages', ['$translateProvider',function ($translateProvider) {
    // Public API here
    return {
      changeLanguage: function (language) {
        $translateProvider.preferredLanguage(language);
      }
    };
  }]);
