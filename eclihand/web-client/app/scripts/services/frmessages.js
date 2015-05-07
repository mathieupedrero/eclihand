'use strict';

/**
 * @ngdoc service
 * @name webClientApp.frMessages
 * @description
 * # frMessages
 * Constant in the webClientApp.
 */
angular.module('webClientApp')
  .constant('frMessages', {
		'common.hello' : 'Bonjour !',
		'common.error' : 'Erreur',
		'common.refresh' : 'Actualiser',
		'common.name' : 'Nom',
		'common.number' : 'Nombre',
		
		'data_table.results_per_page' : '{{pageSize}} résultats par page',
		'data_table.forward_buttons' : 'boutons d\'avance',
		'data_table.backward_buttons' : 'boutons de retour arrière',
		'data_table.page_selection_buttons' : 'boutons de sélection de page'
	});
