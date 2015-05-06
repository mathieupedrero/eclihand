'use strict';

/**
 * @ngdoc filter
 * @name webClientApp.filter:pagination
 * @function
 * @description
 * # pagination
 * Filter in the webClientApp.
 */
angular.module('webClientApp')
  .filter('pagination', function () {
    return function (input, page, pageSize) {
	console.log('pagination input='+input+' page='+page+' pagesize='+pageSize);
		if (input != null){
			var beginning = Math.max(0,(page-1)*pageSize);
			var end =  Math.min(input.length,page*pageSize);
			console.log('elements sélectionnés de '+beginning+' à '+end+' ');
			return input.slice(beginning,end);
		}else{
			return input;
		}
    };
  });
