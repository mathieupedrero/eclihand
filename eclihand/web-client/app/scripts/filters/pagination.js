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
    return function (input, params) {
	console.log('pagination input='+input+' page='+params.page+' pagesize='+params.pageSize);
		if (input != null){
			var beginning = Math.max(0,(params.page-1)*params.pageSize);
			var end =  Math.min(input.length,params.page*params.pageSize);
			console.log('elements sélectionnés de '+beginning+' à '+end+' ');
			return input.slice(beginning,end);
		}else{
			return input;
		}
    };
  });
