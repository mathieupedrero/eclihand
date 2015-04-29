'use strict';

/**
 * @ngdoc service
 * @name webClientApp.messageStack
 * @description
 * # messageStack
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('messageStack', function () {

    var messages = [];

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
			})
		},
		
		removeMessage : function(index){
			messages.splice(index,1);
		},
		
		getMessages : function(){
			return messages;
		}
    };
  });
