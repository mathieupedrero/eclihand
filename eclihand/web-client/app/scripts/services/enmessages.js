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
    'eclihand.title': 'Eclihand',
    'eclihand.toggle_navigation': 'Toggle navigation',

    'common.hello': 'Hello {{userName}}!',
    'common.error': 'Error',
    'common.refresh': 'Refresh',
    'common.name': 'Name',
    'common.number': 'Number',

    'data_table.results_per_page': '{{pageSize}} results per page',
    'data_table.forward_buttons': 'forward buttons',
    'data_table.backward_buttons': 'backward buttons',
    'data_table.page_selection_buttons': 'page selection button'
  });
