'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclListToolTip
 * @description
 * # eclListToolTip
 */
angular.module('webClientApp')
  .directive('eclListToolTip', ['eclUtils', '$compile',
    function(eclUtils, $compile) {
      return {
        restrict: "E",
        transclude: true,
        replace: true,
        template: function(elem, attr) {
          return '<div data-toggle="tooltip"  data-placement="right"  data-html="true" container="body" title="<ul><li>message</li></ul>" ng-transclude>{{' + attr['value'] + '}}</div>';
        },
        link: function postLink(scope, element, attrs) {
          element.tooltip({
            animated: 'fade',
          });
        }
      };
    }
  ]);
