'use strict';

/**
 * @ngdoc service
 * @name webClientApp.messageStack
 * @description
 * # messageStack
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('messageStack',['$modal', function ($modal) {

    var messages = [];
	
	var modal;

    // Public API here
    return {
		Criticity : {
			INFO : 0,
			WARN : 1,
			ERROR : 2
		},
		
		addMessage : function(criticity,message){
			messages.push({
				criticity:criticity,
				message:message
			});
			
			if (modal==null){
				modal = $modal.open({
				  templateUrl: 'messageModalContent.html',
				  controller: 'MessagestackCtrl'
				});
			}
		},
		
		removeMessage : function(index){
			messages.splice(index,1);
			
			if (messages.length==0 && modal != null){
				modal.close();
			}
		},
		
		getMessages : function(){
			return messages;
		}
    };
  }]);
