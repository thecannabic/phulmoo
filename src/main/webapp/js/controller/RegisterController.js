app
	.controller(
		'RegisterController', [
			'$scope',
			'$http',
			'$window',
			'$location',
			function($scope, $http, $uibModal, $window, $location) {

				$scope.registerUser = function() {
					
					var data = {
							"username" : "",
							"firstName" : "Sunny",
							"lastName" : "Kathania",
							"email" : "sunny@phulmoo.com",
							"password" : "Phulmoo1234"
						};
					$http({
						method : 'POST',
						url : URL + FN_REGISTER,
						crossDomain : true,
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json'
						},
						data : JSON.stringify(data),
						dataType : 'json',
					})
						.then(
							function mySuccess(response) {
								console.log(response);
								if (response.data) {
									
								}
								hideLoadMask();
							},
							function myError(response) {
								hideLoadMask();
								showPageRetry();
							});

					
				}
			}
		]);