'use strict';

/**
 * @ngdoc directive
 * @name webClientApp.directive:eclListToolTip
 * @description
 * # eclListToolTip
 */
angular.module('webClientApp')
  .directive('eclListToolTip', function () {
    return {
		restrict: 'A',
		scope: {elements: '=eclListToolTip'},
		link: function postLink(scope, element, attrs) {
			console.log('element = '+element);
			console.log(element.attributes);
			
			element.attr("data-toggle","tooltip");
			element.attr("data-placement","right");
			element.attr("data-html","true");
			element.attr("container","body");
			
			var updateHtml = function(){
				var htmlList = '<ul>';
				for (var i = 0; i<scope.elements.length; i++){
					htmlList += '<li>'+scope.elements[i]+'</li>';
				}
				htmlList+='</ul>'
				return htmlList;
			};
			
			element.attr("title",updateHtml());
			
			$('div[data-toggle="tooltip"]').tooltip({
				animated: 'fade',
				placement: 'bottom',
			});
			
		}
    };
  });
