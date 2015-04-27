'use strict';

/**
 * @ngdoc service
 * @name webClientApp.RequestUtils
 * @description
 * # RequestUtils
 * Factory in the webClientApp.
 */
angular.module('webClientApp')
  .factory('requestUtils', 
	function requestUtils() {

    // Public API here
    return {
		authMethods : {
			NO_ONE : 0,
			SESSION_TOKEN : 1
		},
		
        configBuilder: function() {
			return {
				config : {
					headers:{}
				},
	
				defineMethod :function (method){
					this.config['method'] = method;
					return this;
				},
			
				defineUrl: function(root,uri){
					this.config['url'] = root+uri;
					this.config['X-ecli-uri'] = uri;
					return this;
				},
			
				defineAuthMethod: function(authMethod){
					this.config['X-ecli-authMehod'] = authMethod;
					return this;
				},
			
				defineData: function(data){
					this.config['data'] = content;
					return this;
				},
			
				defineContentType: function(contentType){
					this.config['headers']['Content-Type'] = contentType;
					return this;
				},
			
				defineXEcliDate: function(date){
					this.config['headers']['X-ecli-Date'] = date;
					return this;
				},
			
				build: function(){
					return this.config;
				}
			}
		},
	
	
		signRequest: function(config, userName, token) {
			console.log(config);
			var method = config.method;
			var content = '';
			var contentType = '';
			if (config.data != null) {
			  content = CryptoJS.SHA256(config.data).toString(CryptoJS.enc.Base64);
			}
			if (config.headers['contentType'] != null) {
			  contentType = config.headers['contentType'];
			}

			var date = config.headers["X-ecli-Date"];
			var uri = config["X-ecli-uri"];

			console.log(method);
			console.log(content);
			console.log(contentType);
			console.log(date);
			console.log(uri);

			var message = method + content + contentType + date + uri;
			console.log(message);
			var signature = CryptoJS.HmacSHA256(message, token).toString(CryptoJS.enc.Base64);
			console.log(signature);

			config.headers["Authorization"] = userName + ":" + signature;

			return config;
		}
    };
  });
