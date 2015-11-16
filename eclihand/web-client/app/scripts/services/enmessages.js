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

    'eclihand.error.required': 'Required field',
    'eclihand.error.minlength': 'Too short value',
    'eclihand.error.maxlength': 'Too long value',
    'eclihand.error.email': 'Invalid e-mail address',
    'eclihand.error.number': 'Numbers only',

    'common.hello': 'Hello {{userName}}!',
    'common.error': 'Error',
    'common.refresh': 'Refresh',
    'common.name': 'Name',
    'common.number': 'Number',
    'common.mandatory_field': 'Field is mandatory',
    'common.http_error': 'HTTP Error [{{status}}]',
    'common.http_status': 'Status [{{statusText}}]',
    'common.close': 'Close',
    'common.validate': 'Validate',
    'common.login': 'Login',
    'common.password': 'Password',
    'common.mail_address': 'e-mail address',

    'data_table.results_per_page': '{{pageSize}} results per page',
    'data_table.forward_buttons': 'forward buttons',
    'data_table.backward_buttons': 'backward buttons',
    'data_table.page_selection_buttons': 'page selection button',
    
    'menu.home': 'Home',
    'menu.registration': 'Registration',
    'menu.contact': 'Contact',
	
	'registration.title': 'Welcome on Eclihand',
	'registration.subtitle': 'Create new account',
	'registration.confirm_password': 'Confirm Password',
	'registration.why_register': 'Registration allows you to create the page of your favourite team, or to join its network on Eclihand if it allready exists.'
  });
