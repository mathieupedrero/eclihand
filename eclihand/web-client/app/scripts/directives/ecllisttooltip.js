'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclListToolTip
 * @description
 * # eclListToolTip
 */
angular.module('webClientApp')
  .directive('eclListToolTip', ['eclUtils' ,function(eclUtils) {
    return {
      restrict: 'A',
      scope: {
        elements: '=eclListToolTip'
      },
      link: function postLink(scope, element, attrs) {
	  console.log('transmisisonnnn :'+scope);
	  console.log(scope);
	  
        element.attr("data-toggle", "tooltip");
        element.attr("data-placement", "right");
        element.attr("data-html", "true");
        element.attr("container", "body");

        var updateHtml = function() {
          if (!eclUtils.isNullOrEmpty(scope.elements)) {
			var htmlList = '<ul>';
            for (var i = 0; i < scope.elements.length; i++) {
              htmlList += '<li>' + scope.elements[i] + '</li>';
            }
            htmlList += '</ul>';
			console.log('new tooltip'+htmlList);
			element.attr("title", htmlList);
          }else{
			console.log('new tooltip empty');
			element.attr("title", null);
		  }
        };


        var myElement = $('div[data-toggle="tooltip"]');


        updateHtml();
        element.tooltip({
          animated: 'fade',
        });

        scope.$watch('elements', function(newVal, oldVal) {
		  var newValEmpty = eclUtils.isNullOrEmpty(newVal);
		  var oldValEmpty = eclUtils.isNullOrEmpty(oldVal);
		  if (!newValEmpty){
			if (oldValEmpty){
			  element.tooltip({
				animated: 'fade',
			  });
			}else{
			  element.tooltip('fixTitle');
			}
		  }else if (!oldValEmpty){
			element.tooltip('destroy');
		  }
		  console.log('newVal:'+newVal);
		  console.log('oldVal:'+oldVal);
          updateHtml();
        });

      }

    };
  }]);
