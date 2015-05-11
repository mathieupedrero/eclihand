'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclListToolTip
 * @description
 * # eclListToolTip
 */
angular.module('webClientApp')
  .directive('eclListToolTip', function() {
    return {
      restrict: 'A',
      scope: {
        elements: '=eclListToolTip'
      },
      link: function postLink(scope, element, attrs) {
        element.attr("data-toggle", "tooltip");
        element.attr("data-placement", "right");
        element.attr("data-html", "true");
        element.attr("container", "body");

        var updateHtml = function() {
          var htmlList = '';
          if (scope.elements.length > 0) {
            htmlList += '<ul>';
            for (var i = 0; i < scope.elements.length; i++) {
              htmlList += '<li>' + scope.elements[i] + '</li>';
            }
            htmlList += '</ul>';
          }
          element.attr("title", htmlList);
        };


        var myElement = $('div[data-toggle="tooltip"]');


        updateHtml();
        element.tooltip({
          animated: 'fade',
        });

        scope.$watch('elements', function(newVal, oldVal) {
          updateHtml();
          element.tooltip('fixTitle');
        });

      }

    };
  });
