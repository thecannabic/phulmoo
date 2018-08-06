angular.module('Phulmoo', [])
	.controller(
		'CatalogDetailController', [
			'$scope',
			'$http',
			'$window',
			'$location',
			function ($scope, $http,$window,
			$location) {
				var data= location.search.toString().split('=')[1];
				console.log(data);
				console.log('Detail Controller Working');
				$scope.shotDes="abc,sfhsfhsk,hskhfskhfs,skhfsf";
				$scope.getProductDetails = function () {
					$http({
							method: 'POST',
							url: URL + FN_PRODUCT_DETAIL,
							crossDomain: true,
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
							data:  data,
							dataType: 'json',
						})
						.then(
							function (response) {
								//console.log('response',response);
								$scope.productInfo=response.data.responseData;
								
									$scope.productInfo.productImage="images/banner-"+$scope.productInfo.productID+".jpg";
									//console.log($scope.products[i].productImage);
								
								console.log($scope.productInfo);
							},
							function (response) {
								//hideLoadMask();
								console.log('response',response);
								//showPageRetry();
							});
				};
				$scope.insertOrder = function (productInfo) {
					var pData=productInfo;
					pData.productSKU=sessionStorage.getItem("userId");
					$http({
							method: 'POST',
							url: URL + FN_INSERT_ORDER,
							crossDomain: true,
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
							data:  pData,
							dataType: 'json',
						})
						.then(
							function (response) {
								console.log('response',response);
								var data=response.data.responseData;
								//$scope.productInfo;
								if(data){
								//alert(data.orderID);
								var oId=parseInt(data.orderID);
								$window.location.href="/PhulmooServer/orderDetail.html?tempoid="+oId;
								
									//$scope.productInfo.productImage="images/banner-"+$scope.productInfo.productID+".jpg";
									//console.log($scope.products[i].productImage);
								}
								//console.log($scope.productInfo);
							},
							function (response) {
								//hideLoadMask();
								console.log('response',response);
								//showPageRetry();
							});
				};
				$scope.getProductDetails();
				$scope.orderProduct =function(product){
					console.log(product);
					var userName=sessionStorage.getItem("usrn");
					var firstName=sessionStorage.getItem("fname");
					var token=sessionStorage.getItem("tkn");
					if(token!=null&&userName!=null){
						//insert the product in orders table for the current user
						console.log('user logged in');
						//insert the data and move to order details
						$scope.insertOrder(product);
						
					}
					else{
						//ask to login or continue as guest
						//if not logged in insert the data in orders based on the ip address of the user
						console.log('user not logged in');
						sessionStorage.setItem("v-pid",product.productID);
						$window.location.href="/PhulmooServer/login.html";
					}
					//finally
					//proceed to order details
					
				};
				$scope.addToCart =function(product){
					console.log(product);
				};
				$scope.continueShopping =function(product){
					$window.location.href="/PhulmooServer/#catalogs";
				};
			}
		]);
		