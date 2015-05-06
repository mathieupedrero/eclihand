'use strict';

/**
 * @ngdoc service
 * @name webClientApp.enMessages
 * @description
 * # enMessages
 * Constant in the webClientApp.
 */
angular.module('webClientApp')
  .constant('enMessages', {
		'common.hello' : 'Hello!',
		'common.error' : 'Error',
		'common.refresh' : 'Refresh',
		'common.name' : 'Name',
		'common.number' : 'Number',
		
		'data_table.results_per_page' : '{{pageSize}} results per page'
	});
