app
	.controller(
		'HomeController', [
			'$scope',
			'$http',
			function ($scope, $http) {
				var data= {"username":"sunny@phulmoo.com","firstName":"", "lastName":"", "email":"", "sex":"", "password":"", "userId":"", "token":"", "isAdmin":""};
				$scope.authenticate = function () {
					$http({
							method: 'POST',
							url: "http://172.20.10.4:8080/PhulmooServer/rest/phulmooUser/token",
							crossDomain: true,
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
							data:  JSON.stringify(data),
							dataType: 'json',
						})
						.then(
							function (response) {
								console.log('response',response);
							},
							function (response) {
								//hideLoadMask();
								console.log('response',response);
								//showPageRetry();
							});
				};
				//$scope.authenticate();
				
			}
		]);