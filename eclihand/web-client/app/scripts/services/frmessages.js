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
    'eclihand.title': 'Eclihand',
    'eclihand.toggle_navigation': 'Bouton d\'affichage du menu',

    'eclihand.error.required': 'Champ requis',
    'eclihand.error.minlength': 'Valeur trop courte',
    'eclihand.error.maxlength': 'Valeur trop longue',
    'eclihand.error.email': 'E-mail invalide',
    'eclihand.error.number': 'Caractères alphabétiques non valides',


    'common.hello': 'Bonjour {{userName}}!',
    'common.error': 'Erreur',
    'common.refresh': 'Actualiser',
    'common.name': 'Nom',
    'common.number': 'Nombre',
    'common.mandatory_field': 'Le champs est obligatoire',
    'common.http_error': 'Erreur HTTP [{{status}}]',
    'common.http_status': 'Status [{{statusText}}]',
    'common.close': 'Fermer',
    'common.validate': 'Valider',

    'data_table.results_per_page': '{{pageSize}} résultats par page',
    'data_table.forward_buttons': 'boutons d\'avance',
    'data_table.backward_buttons': 'boutons de retour arrière',
    'data_table.page_selection_buttons': 'boutons de sélection de page',
    
    
    'menu.home': 'Accueil',
    'menu.registration': 'S\'inscrire',
    'menu.contact': 'Contact'
  });
