angular.module('Phulmoo', [])
	.controller(
		'RegistrationLoginController', [
			'$scope',
			'$http',
			function($scope, $http) {
				var data = "1";

				var data = {
					"username" : "thecannabic@gmail.com",
					"firstName" : "Sunny",
					"lastName" : "",
					"email" : "",
					"sex" : "",
					"password" : "",
					"userId" : "",
					"token" : "",
					"isAdmin" : ""
				};
				$scope.login = function() {
					$http({
						method : 'POST',
						data : JSON.stringify(data),
						url : URL + FN_TOKEN,
						crossDomain : true,
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json'
						},
						dataType : 'json',
					})
						.then(
							function(response) {
								//console.log('response',response);
								console.log(response);
								var data=response.data.responseData;
								if (data) {
									
									if (data.token) {
										TOKEN = data.token;
								
										sessionStorage.setItem("usrn", data.username);
										sessionStorage.setItem("fname", data.firstName);
										sessionStorage.setItem("tkn", data.token);
										sessionStorage.setItem("userId",data.userId);
										
										
									}
								}
								var vpid=sessionStorage.getItem("v-pid");
								if(vpid!=null){
								window.location.href = "/PhulmooServer/catalog-detail.html?pid="+vpid;
								}
								else{
									window.location.href = "/PhulmooServer";
								}
							},
							function(response) {
								//hideLoadMask();
								console.log('response', response);
							//showPageRetry();
							});
				};
				//$scope.getProductDetails();

			}
		]);