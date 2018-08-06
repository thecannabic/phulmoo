angular.module('Phulmoo', [])
	.controller(
		'cartController', [
			'$scope',
			'$http',
			'$window',
			'$location',
			function($scope, $http, $window, $location) {
			    function onLoad() {
				$scope.products = [];
				$scope.carts = [];
				$scope.total = 0;
				productData();
			}
			onLoad();

			    $scope.addCart = addCart;
			    $scope.getTotals = getTotals;
			    $scope.removeCart = removeCart;
				
				function productData() {
					var data = {
					  "items": [{
						"product": {
						  "name": "Suit A",
						  "price": {
						  "value": 1999,
						  }
						}
					  },
					  {
						"product": {
							"name": "Suit B",
							"price": {
							"value": 2999,
							}
						}
					  }]
					}
					parseProducts(data.items);
				  }
				  function getProducts() {
				$http.get('data.json')
				  .success(function(data) { parseProducts(data.items) })
			  }

			  function parseProducts(items) {
				angular.forEach(items, function(item, index) {
				  $scope.products.push(item.product);
				});
			  }

			  function addCart(product){
				if(product){
				  $scope.carts.push({
					p_name: product.name, 
					p_price:product.price.value
				  });
				} 
			  }

			  function getTotals(){
				var initialValue = 0;
				$scope.total = $scope.carts.reduce((x,y) => x + y["p_price"], initialValue);
			  }

			  function removeCart(i){
				$scope.carts.splice(i, 1);
				getTotals();
			  }
			}
		]);