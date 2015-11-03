'use strict';

/**
 * @ngdoc service
 * @name webClientApp.messageStack
 * @description
 * # messageStack
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('messageStack',function () {

    var messages = [];
	
	var modal;

    // Public API here
    return {
		Criticity : {
			INFO : 0,
			WARN : 1,
			ERROR : 2
		},
		
		addMessage : function(criticity,title,message){
			messages.push({
				criticity:criticity,
				title:title,
				message:message
			});
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
  });
