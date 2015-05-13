'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclListToolTip
 * @description
 * # eclListToolTip
 */
angular.module('webClientApp')
  .directive('eclListToolTip', ['eclUtils','$compile' ,function(eclUtils,$compile) {
    return {
      restrict: 'A',
	  template: function (elem, attr){
		return '<div data-toggle="tooltip"  data-placement="right"  data-html="true" container="body" title="<ul><li>message</li></ul>">{{'+attr['eclListToolTip']+'}}</div>';
	  },
      link: function postLink(scope, element, attrs) {
        element.tooltip({
          animated: 'fade',
        });
      }
    };
  }]);
