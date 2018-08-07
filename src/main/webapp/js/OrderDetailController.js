angular
		.module('Phulmoo', [])
		.controller(
				'OrderDetailController',
				[
						'$scope',
						'$http',
						'$window',
						'$location',
						function($scope, $http, $uibModal, $window, $location) {
							console.log('OrderDetailController');
							$scope.OrderEmail = "thecannabic@gmail.com";
							$scope.OrderShipName = "sdsfd";
							$scope.OrderShipAddress = "# 114 sector 77";
							$scope.OrderCity = "Hamirpur";
							$scope.OrderZip = "176210";
							$scope.OrderPhone = "9781942261";
							$scope.OrderId = 0;

							$scope.billingInfo = function() {
								console.log('sending billing info');

								// var
								// order={"OrderID":$scope.orderModel.address.orderId};
								var orderUIModal = {
									"products" : $scope.products,
									"address" : $scope.address
								};
								$http({
									method : 'POST',
									url : URL + FN_BILLING_INFO,
									crossDomain : true,
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									},
									data : orderUIModal,
									dataType : 'json',
								})
										.then(
												function mySuccess(response) {
													console.log(response);

													$scope
															.sendMail(response.data.responseData);

												}, function myError(response) {
													console.log(response);
												});

							};

							$scope.getOrders = function() {
								var userId = sessionStorage.getItem("userId");
								var params = parseQueryString();
								var str = params["tempoid"];
								var data = parseInt(str);
								console.log(data);
								$http({
									method : 'POST',
									url : URL + FN_ORDER_DETAIL,
									crossDomain : true,
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									},
									data : data,
									dataType : 'json',
								})
										.then(
												function mySuccess(response) {
													console.log(response);

													if (response.data) {
														$scope.address = response.data.responseData.address;
														$scope.products = response.data.responseData.products;

													}
												}, function myError(response) {
													console.log(response);
													hideLoadMask();
													showPageRetry();// startPay();
												});

							};

							$scope.getOrders();
							$scope.sendMail = function(filePath) {
								$http(
										{
											method : 'POST',
											url : URL + FN_SEND_MAIL,
											crossDomain : true,
											headers : {
												'Accept' : 'application/json',
												'Content-Type' : 'application/json'
											},
											data : "thecannabic@gmail.com,SAMPLE,SAMPLE BODY,"
													+ filePath,
											dataType : 'json',
										})
										.then(
												function mySuccess(response) {
													console.log(response);

													if (response.data.responseData
															&& response.data.responseData == "Done") {
														window.location.href = "/Phulmoo";
													}
												}, function myError(response) {
													console.log(response);
												});
							}
							$scope.validateAndProcessOrder = function(input) {
								// need to validate
								var orderInfo = {
									"emailAddress" : $scope.OrderEmail,
									"name" : $scope.OrderShipName,
									"homeAddress" : $scope.OrderShipAddress,
									"postalCode" : $scope.OrderZip,
									"phoneNumber" : $scope.OrderPhone,
									"city" : $scope.OrderCity
								};

								$scope.address.emailAddress = $scope.OrderEmail;
								$scope.address.name = $scope.OrderShipName;
								$scope.address.homeAddress = $scope.OrderShipAddress;
								$scope.address.postalCode = $scope.OrderZip;
								$scope.address.phoneNumber = $scope.OrderPhone;
								$scope.address.city = $scope.OrderCity;

								var orderUIModal = {
									"products" : $scope.products,
									"address" : $scope.address
								};

								$http({
									method : 'POST',
									url : URL + FN_CONFIRM_ORDER,
									crossDomain : true,
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									},
									data : orderUIModal,
									dataType : 'json',
								})
										.then(
												function mySuccess(response) {
													console.log(response);

													if (response.data) {
														$scope.products = response.data.responseData;
														if (true) {
															startPay();
														}
													}
												}, function myError(response) {
													console.log(response);
												});

							};

						} ]);