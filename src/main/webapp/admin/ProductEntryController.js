app
	.controller(
		'ProductEntryController', [
			'$scope',
			'$http',
			'$window',
			'$location',
			function ($scope, $http, $uibModal, $window, $location) {
				$http({
					method : 'POST',
					url : URL + FN_PRODUCT_LIST,
					crossDomain : true,
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					data : "11",
					dataType : 'json',
				})
					.then(
						function mySuccess(response) {
							console.log(response);
							if (response.data) {
								$scope.products = response.data.responseData;
								for(var i=0;i<$scope.products.length;i++){
									$scope.products[i].productImage="images/banner-"+$scope.products[i].productID+".jpg";
								}
							}
							hideLoadMask();
						},
						function myError(response) {
							hideLoadMask();
							showPageRetry();
						});
				$scope.loadProductDetail = function(productObj) {
					console.log("selected item", productObj);
					 $window.location.url=window.location.host+"/catalog-detail.html";
				}
			}
			
		]);
		