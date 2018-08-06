app
	.controller(
		'CatalogsController', [
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
									//console.log($scope.products[i].productImage);
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
					/*$http.get("/catalog-detail.html",                                       // 1. url
					                                                    // 2. request body
					          { params: productObj.productId }   // 3. config object
					);*/
					 $window.location.url=window.location.host+"/catalog-detail.html";
				}
			}
			/*$scope.product = [];
			function addProduct() {

				$scope.product.push({name:$scope.nameProduct, price:$scope.priceProduct});
				$scope.nameProduct = "";
				$scope.priceProduct = "";
			 }*/
			
		]);
		